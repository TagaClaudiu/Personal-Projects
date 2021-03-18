#include <utility.h>
#include <formatio.h>
#include <ansi_c.h>
#include <advanlys.h>
#include <cvirte.h>		
#include <userint.h>
#include "WaveFilter.h"

#define SAMPLE_RATE		0
#define NPOINTS			1

int waveInfo[2]; //waveInfo[0] = sampleRate
				 //waveInfo[1] = number of elements
double sampleRate = 0.0;
int npoints = 0;
double *waveData = 0;
double *filteredData = 0;
unsigned int secStart = 0;
unsigned int secStop = 1;
char fileName[100];
WindowConst windowConstants;
double *autoSpectrum = 0;
double *filteredSpectrum = 0;
double *rawSpectrum = 0;
double *convertedSpectrum = 0;
double freqPeak = 0;
double powerPeak = 0;

static int mainPanel;
static int frqPanel;

int SignalSpectrum(double inputArray[], ssize_t numberOfElements, double samplingFrequency, double xOutputArray[], double yOutputArray[]);


int main (int argc, char *argv[])
{
	if (InitCVIRTE (0, argv, 0) == 0)
		return -1;	/* out of memory */
	if ((mainPanel = LoadPanel (0, "WaveFilter.uir", MAIN_PANEL)) < 0)
		return -1;
	if ((frqPanel =  LoadPanel (0, "WaveFilter.uir", FRQ_PANEL)) < 0)
		return -1;
	DisplayPanel (mainPanel);
	RunUserInterface ();
	DiscardPanel (mainPanel);
	DiscardPanel(frqPanel);
	return 0;
}

void Mediere(int n)
{
	int i, j;
	double sum = 0;

	for ( i = 0; i < npoints; i++ )
	{
		for (j = 0; j< n; j++ )
		{
			if (i-j >= 0)
				sum+=waveData[i-j];
		}
		
		filteredData[i] = sum/n;
		sum = 0;
	}
	

}


void FiltrareAlpha(double alpha)
{
	int i;
	
	filteredData[0] = waveData[0];
	
	for (i = 1; i< npoints; i++)
	{
		filteredData[i] = (1-alpha)*filteredData[i-1] + alpha * waveData[i];	
	}
}

int zeroCrossing()
{
	int nr = 0;
	for (int i = 1; i<npoints; i++)
	{
		if (waveData[i] * waveData[i-1] < 0)
			nr++;
	}
	return nr;
}

int CVICALLBACK OnMainPanel (int panel, int event, void *callbackData,
							 int eventData1, int eventData2)
{
	switch (event)
	{
		case EVENT_GOT_FOCUS:

			break;
		case EVENT_LOST_FOCUS:

			break;
		case EVENT_CLOSE:
			QuitUserInterface (0);
			break;
	}
	return 0;
}

int CVICALLBACK OnLoadButtonCB (int panel, int control, int event,
								void *callbackData, int eventData1, int eventData2)
{
	double maxVal = 0.0;
	double minVal = 0.0;
	int maxIndex = 0;
	int minIndex = 0;
	double mean = 0.0;
	double median = 0.0;
	double dispersion = 0.0;
	
	int nrZeroCrossing = 0;
	
	
		switch (event)
	{
		case EVENT_COMMIT:
			//executa script python pentru conversia unui fisierului .wav in .txt
			//LaunchExecutable("python main.py");
			
			//astept sa fie generate cele doua fisiere (modificati timpul daca este necesar
			//Delay(4);
			
			//incarc informatiile privind rata de esantionare si numarul de valori
			FileToArray("wafeInfo.txt", waveInfo, VAL_INTEGER, 2, 1, VAL_GROUPS_TOGETHER, VAL_GROUPS_AS_COLUMNS, VAL_ASCII);
			sampleRate = waveInfo[SAMPLE_RATE];
			npoints = waveInfo[NPOINTS];
			
			//alocare memorie pentru numarul de puncte
			waveData = (double *) calloc(npoints, sizeof(double));
			
			filteredData =  (double *) calloc(npoints, sizeof(double));
			
			//incarcare din fisierul .txt in memorie (vector)
			FileToArray("waveData.txt", waveData, VAL_DOUBLE, npoints, 1, VAL_GROUPS_TOGETHER, VAL_GROUPS_AS_COLUMNS, VAL_ASCII);
			
			//afisare pe graf
			PlotY(panel, MAIN_PANEL_IDC_GRAPH_RAW_DATA, waveData, npoints, VAL_DOUBLE, VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID, VAL_CONNECTED_POINTS, VAL_RED);
			
			MaxMin1D(waveData, npoints, &maxVal, &maxIndex, &minVal, &minIndex);
			Mean(waveData, npoints, &mean);
			Median(waveData, npoints, &median);
			StdDev(waveData, npoints, &mean, &dispersion);
			nrZeroCrossing = zeroCrossing();
			
			SetCtrlVal(panel, MAIN_PANEL_NUM_MAX, maxVal);
			SetCtrlVal(panel, MAIN_PANEL_NUM_MIN, minVal);
			SetCtrlVal(panel, MAIN_PANEL_NUM_MEAN, mean);
			SetCtrlVal(panel, MAIN_PANEL_NUM_MEDIAN, median);
			SetCtrlVal(panel, MAIN_PANEL_NUM_DISPERSION, dispersion);
			SetCtrlVal(panel, MAIN_PANEL_NUM_ZEROCROSS, nrZeroCrossing);
			
			break;
	}
	return 0;
}

int CVICALLBACK OnGraphRaw (int panel, int control, int event,
							void *callbackData, int eventData1, int eventData2)
{
	switch (event)
	{
		case EVENT_COMMIT:

			break;
	}
	return 0;
}

int CVICALLBACK OnFilterSelect (int panel, int control, int event,
								void *callbackData, int eventData1, int eventData2)
{
	int val;
	switch (event)
	{
		case EVENT_COMMIT:
			GetCtrlVal(panel, MAIN_PANEL_RING_FILTER, &val);
			if (val == 0)
			{
				SetCtrlAttribute(panel, MAIN_PANEL_NUM_N, ATTR_VISIBLE, 1);
				SetCtrlAttribute(panel, MAIN_PANEL_NUM_ALPHA, ATTR_VISIBLE, 0);
			}
			else if (val == 1)
			{
				SetCtrlAttribute(panel, MAIN_PANEL_NUM_N, ATTR_VISIBLE, 0);
				SetCtrlAttribute(panel, MAIN_PANEL_NUM_ALPHA, ATTR_VISIBLE, 1);
			}
			break;
	}
	return 0;
}

int CVICALLBACK OnApplyCB (int panel, int control, int event,
						   void *callbackData, int eventData1, int eventData2)
{
	int val;
	double n;
	double alpha;

	switch (event)
	{
		case EVENT_COMMIT:
			GetCtrlVal(panel, MAIN_PANEL_RING_FILTER, &val);
			
			if (filteredData != 0)
			{
				for (int i = 0; i<npoints; i++)
					filteredData[i] = 0;
			}
			
			 if(val == 0)
			 {
				 GetCtrlVal(panel, MAIN_PANEL_NUM_N, &n);
				 Mediere(n);  
			 }
			 else
			 {
				 GetCtrlVal(panel, MAIN_PANEL_NUM_ALPHA, &alpha);
				 FiltrareAlpha(alpha); 
			 }
			
			double* pointer = filteredData + (int)(secStart*sampleRate);
			
			DeleteGraphPlot(panel, MAIN_PANEL_IDC_GRAPH_FILTER_DATA, -1, VAL_IMMEDIATE_DRAW);
			PlotY(panel, MAIN_PANEL_IDC_GRAPH_FILTER_DATA, pointer, (secStop - secStart)*sampleRate, VAL_DOUBLE, VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID, VAL_CONNECTED_POINTS, VAL_RED);
			
			
			int m, d, y, h, min, s;
			int bitmap; 
			GetSystemDate(&m, &d, &y);
			GetSystemTime(&h, &min, &s);
			GetCtrlDisplayBitmap(mainPanel, MAIN_PANEL_IDC_GRAPH_RAW_DATA, 0,&bitmap);
			sprintf(fileName,"Images/RawData-%d.%02d.%02d_%02d-%02d-%02d.jpeg", y, m, d, h, min, s); 
			SaveBitmapToJPEGFile(bitmap, fileName, JPEG_PROGRESSIVE, 100);
			DiscardBitmap(bitmap);
			
			GetCtrlDisplayBitmap(mainPanel, MAIN_PANEL_IDC_GRAPH_FILTER_DATA, 0,&bitmap);
			sprintf(fileName,"Images/FilterData-Interval[%d,%d]-%d.%02d.%02d_%02d-%02d-%02d.jpeg",secStart, secStop, y, m, d, h, min, s); 
			SaveBitmapToJPEGFile(bitmap, fileName, JPEG_PROGRESSIVE, 100);
			DiscardBitmap(bitmap);
			 
			break;
	}
	return 0;
}

int CVICALLBACK OnNextPrevCB (int panel, int control, int event,
							  void *callbackData, int eventData1, int eventData2)
{
	switch (event)
	{
		case EVENT_COMMIT:
			switch(control)
			{
				case MAIN_PANEL_BUTTON_NEXT:
					if (secStop < npoints/sampleRate)
					{
						secStart = secStart + 1;
						secStop = secStop + 1;
					}
					break;
				case MAIN_PANEL_BUTTON_PREV:
					if (secStart > 0)
					{
						secStart = secStart -1;
						secStop = secStop -1;
					}
					break;
			}
			SetCtrlVal(panel, MAIN_PANEL_NUM_START, secStart);
			SetCtrlVal(panel, MAIN_PANEL_NUM_STOP, secStop);
			
			OnApplyCB (panel, control, event, NULL, 0, 0);
			
			break;
	}
	return 0;
}

int CVICALLBACK OnNumSecunde (int panel, int control, int event,
							  void *callbackData, int eventData1, int eventData2)
{
	switch (event)
	{
		case EVENT_COMMIT:
			switch(control)
			{
				case MAIN_PANEL_NUM_START:
					GetCtrlVal(panel, MAIN_PANEL_NUM_START, &secStart);
					break;
				case MAIN_PANEL_NUM_STOP:
					GetCtrlVal(panel, MAIN_PANEL_NUM_STOP, &secStop);
					break;
			}
			
			OnApplyCB (panel, control, event, NULL, 0, 0);
			
			break;
	}
	return 0;
}

int CVICALLBACK OnSwitchPanelCB (int panel, int control, int event,
								 void *callbackData, int eventData1, int eventData2)
{
	switch (event)
	{
		case EVENT_COMMIT:
			if(panel == mainPanel)
			{
				SetCtrlVal(frqPanel, FRQ_PANEL_IDC_SWITCH_PANEL, 1);
				DisplayPanel(frqPanel);
				HidePanel(panel);
			}
			else
			{
				SetCtrlVal(mainPanel, FRQ_PANEL_IDC_SWITCH_PANEL, 0);
				DisplayPanel(mainPanel);
				HidePanel(panel);
			}
			break;
	}
	return 0;
}

int CVICALLBACK OnFrequencyPanel (int panel, int event, void *callbackData,
								  int eventData1, int eventData2)
{
	switch (event)
	{
		case EVENT_GOT_FOCUS:

			break;
		case EVENT_LOST_FOCUS:

			break;
		case EVENT_CLOSE:
			QuitUserInterface (0);
			break;
	}
	return 0;
}

int CVICALLBACK OnGetSpectru (int panel, int control, int event,
							  void *callbackData, int eventData1, int eventData2)
{

	switch (event)
	{
		case EVENT_COMMIT:
			//declarari si initializari part 1
			double dt = 1.0/sampleRate;
			double df;
			char unit[32] = "V";
			int N = 0;
			int secundaS = 0;
			int secundaE = 1;

			

			GetCtrlVal(panel, FRQ_PANEL_IDC_NUM_N, &N);
			GetCtrlVal(panel, FRQ_PANEL_IDC_NUM_SECUNDA, &secundaS);
			GetCtrlVal(panel, FRQ_PANEL_IDC_NUM_SECUNDA_2, &secundaE);
			
			//declarari si initializari part 2
			double *pointer = waveData + (int)(secundaS * sampleRate);
			double *pointer2 = waveData + (int)(2 * sampleRate);

			autoSpectrum = (double *) calloc(N/2, sizeof(double));
			convertedSpectrum = (double *) calloc(N/2, sizeof(double));
			rawSpectrum = (double *) calloc(sampleRate/2, sizeof(double));
			
			double *window = 0;
			double *secundaFiltrata = 0;
			window = (double *) calloc(sampleRate, sizeof(double));
			secundaFiltrata = (double *) calloc(sampleRate, sizeof(double));
			filteredSpectrum = (double *) calloc(sampleRate/2, sizeof(double));
			
			//pt graful nefiltrat
			
			//metoda alternativa de a calcula spectru:
			/*
			double powerSpectrum[N/2];
			double frequencyArray[N/2];
			SignalSpectrum(pointer, N, sampleRate, frequencyArray, powerSpectrum);
			DeleteGraphPlot(panel, FRQ_PANEL_IDC_GRAPH_SPEC_DATA, -1, VAL_IMMEDIATE_DRAW);
			PlotXY (panel, FRQ_PANEL_IDC_GRAPH_SPEC_DATA, frequencyArray, powerSpectrum, N/2, VAL_DOUBLE, VAL_DOUBLE, VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID,1, VAL_DK_GREEN);
			*/
			
			//1
			ScaledWindowEx(pointer, N, RECTANGLE_, -1, &windowConstants);
			//2
			AutoPowerSpectrum(pointer, N, dt, autoSpectrum, &df);
			//3
			PowerFrequencyEstimate(autoSpectrum, N/2, -1, windowConstants, df, 7, &freqPeak, &powerPeak);
			//4
			SpectrumUnitConversion(autoSpectrum, N/2, 0, SCALING_MODE_LINEAR, DISPLAY_UNIT_VRMS, df, windowConstants, convertedSpectrum, unit);
			
			for (int i = 0; i<N/2; i++)
			{
				rawSpectrum[i] = convertedSpectrum[i];
			}
			
			DeleteGraphPlot(panel, FRQ_PANEL_IDC_GRAPH_RAW_DATA, -1, VAL_IMMEDIATE_DRAW);
			//PlotY(panel, FRQ_PANEL_IDC_GRAPH_RAW_DATA, waveData, npoints, VAL_DOUBLE, VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID, VAL_CONNECTED_POINTS, VAL_RED);
			PlotY(panel, FRQ_PANEL_IDC_GRAPH_RAW_DATA, pointer, (secundaE-secundaS)*sampleRate, VAL_DOUBLE, VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID, VAL_CONNECTED_POINTS, VAL_BLUE);
			
			DeleteGraphPlot(panel, FRQ_PANEL_IDC_GRAPH_SPEC_DATA, -1, VAL_IMMEDIATE_DRAW);
			PlotWaveform(panel, FRQ_PANEL_IDC_GRAPH_SPEC_DATA, rawSpectrum, (int)(sampleRate/2), VAL_DOUBLE, 1.0, 0.0, 0.0, 1, VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID, VAL_CONNECTED_POINTS, VAL_BLUE);		
			
			int tip_fereastra = 0;
			GetCtrlVal(panel, FRQ_PANEL_IDC_RING_WINDOW, &tip_fereastra);
			
			//1.1
			LinEv1D(pointer2, sampleRate, 1, 0, window);
			
			//1.2
			if (tip_fereastra == 0)
				WelchWin(window, sampleRate); // o secunda
			else
				GaussWin(window, sampleRate, 0.2);
			
			//1.3
			int select_filtru = 0;
			int select_grad_filtru = 0;
			GetCtrlVal(panel, FRQ_PANEL_IDC_RING_FILTER_TYPE, &select_filtru);
			GetCtrlVal(panel, FRQ_PANEL_IDC_RING_FILTER, &select_grad_filtru);
			if (select_filtru==0)
			{
			int i, j;
			double sum = 0;
			for ( i = 0; i < sampleRate; i++ )
			{
				for (j = 0; j< 16; j++ )
				{
					if (i-j >= 0)
						sum+=window[i-j];
				}
			
				secundaFiltrata[i] = sum/16;
				sum = 0;
			}
			}
			else
			{
				if (select_grad_filtru == 0)
					Bssl_LPF(window,sampleRate,sampleRate,sampleRate/4,4,secundaFiltrata); //frecventa de taiere = 1/2 din spectru
				else
					Bssl_LPF(window,sampleRate,sampleRate,sampleRate/4,6,secundaFiltrata);
			}
			
			double *autoSpectrum2 = (double *) calloc(sampleRate, sizeof(double));
			//2
			AutoPowerSpectrum(secundaFiltrata, sampleRate, dt, autoSpectrum2, &df);
			
			for (int i = 0; i<sampleRate/2; i++)
			{
				filteredSpectrum[i] = autoSpectrum2[i];
			}
			
			
			DeleteGraphPlot(panel, FRQ_PANEL_IDC_GRAPH_FIL_DATA, -1, VAL_IMMEDIATE_DRAW);
			PlotY(panel, FRQ_PANEL_IDC_GRAPH_FIL_DATA, secundaFiltrata, sampleRate, VAL_DOUBLE, VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID, VAL_CONNECTED_POINTS, VAL_RED);
			
			DeleteGraphPlot(panel, FRQ_PANEL_IDC_GRAPH_FISPEC_DATA, -1, VAL_IMMEDIATE_DRAW);
			PlotWaveform(panel, FRQ_PANEL_IDC_GRAPH_FISPEC_DATA, filteredSpectrum, sampleRate/2, VAL_DOUBLE, 1.0, 0.0, 0.0, df, VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID, VAL_CONNECTED_POINTS, VAL_BLUE);	


			SetCtrlVal(panel, FRQ_PANEL_IDC_NUM_FREQPEAK, freqPeak);
			SetCtrlVal(panel, FRQ_PANEL_IDC_NUM_POWERPEAK, powerPeak);
			
			//obtinere imagini
			int m, d, y, h, min, s;
			int bitmap; 
			GetSystemDate(&m, &d, &y);
			GetSystemTime(&h, &min, &s);
			
			//raw image
			GetCtrlDisplayBitmap(frqPanel, FRQ_PANEL_IDC_GRAPH_RAW_DATA, 0,&bitmap);
			sprintf(fileName,"ImagesPart2/Spectrum-RawData-Interval[%d,%d]-%d.%02d.%02d_%02d-%02d-%02d.jpeg",secundaS, secundaE, y, m, d, h, min, s); 
			SaveBitmapToJPEGFile(bitmap, fileName, JPEG_PROGRESSIVE, 100);
			DiscardBitmap(bitmap);
			//raw spectrum
			GetCtrlDisplayBitmap(frqPanel, FRQ_PANEL_IDC_GRAPH_SPEC_DATA, 0,&bitmap);
			sprintf(fileName,"ImagesPart2/RawData-Interval[%d,%d]-%d.%02d.%02d_%02d-%02d-%02d.jpeg",secundaS, secundaE, y, m, d, h, min, s); 
			SaveBitmapToJPEGFile(bitmap, fileName, JPEG_PROGRESSIVE, 100);
			DiscardBitmap(bitmap);
			//filtered image
			GetCtrlDisplayBitmap(frqPanel, FRQ_PANEL_IDC_GRAPH_FIL_DATA, 0,&bitmap);
			sprintf(fileName,"ImagesPart2/FilteredData-Interval[%d,%d]-%d.%02d.%02d_%02d-%02d-%02d.jpeg",2, 3, y, m, d, h, min, s); 
			SaveBitmapToJPEGFile(bitmap, fileName, JPEG_PROGRESSIVE, 100);
			DiscardBitmap(bitmap);
			//filtered spectrum
			GetCtrlDisplayBitmap(frqPanel, FRQ_PANEL_IDC_GRAPH_FISPEC_DATA, 0,&bitmap);
			sprintf(fileName,"ImagesPart2/Spectrum-FilteredData-Interval[%d,%d]-%d.%02d.%02d_%02d-%02d-%02d.jpeg",2, 3, y, m, d, h, min, s); 
			SaveBitmapToJPEGFile(bitmap, fileName, JPEG_PROGRESSIVE, 100);
			DiscardBitmap(bitmap);
			
			break;
	}
	return 0;
}

//metoda alternativa de a calcula spectru
int SignalSpectrum(double inputArray[], ssize_t numberOfElements, double samplingFrequency, double xOutputArray[], double yOutputArray[])
{
	int err = 0;
	double WfR[numberOfElements];
	double WfI[numberOfElements];
	double p[numberOfElements];
	double ps[numberOfElements];
	double frequency_array[numberOfElements];

	for(int i = 0; i < numberOfElements; i++)
	{ 
		WfR[i] = inputArray[i]; //partea reala
		WfI[i] = 0; //partea imaginara o in cazul dat
	}
	FFT(WfR, WfI, numberOfElements);
	
	for (int i = 0; i < numberOfElements - 1; i++)
	{ 
		//spectrul dupa formula
		p[i] = WfR[i] * WfR[i] + WfI[i] * WfI[i];
		ps[i] = p[i] / pow((double)numberOfElements,2.0);
	}
	//Spectrum (wfm3, 1024);//Func?ie CVI- se poate incerca ca alternativa
	double delta_t = 1.0 / samplingFrequency; //pas in timp
	double delta_f = 1.0 / (numberOfElements * delta_t); //pas in frecventa
	
	//construiesc axa pentru frecventa
	frequency_array[0] = 0.0;
	frequency_array[numberOfElements - 1] = 1.0 / (2 * delta_t);
	for (int i = 0; i < numberOfElements / 2; i++)
	{
		frequency_array[i] = i * delta_f;
		frequency_array[numberOfElements - 1 - i] = -1 * delta_f;
	}

	Copy1D (frequency_array, numberOfElements/2, xOutputArray);
	Copy1D (ps, numberOfElements/2, yOutputArray);

	return err;
}
