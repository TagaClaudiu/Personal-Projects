/**************************************************************************/
/* LabWindows/CVI User Interface Resource (UIR) Include File              */
/*                                                                        */
/* WARNING: Do not add to, delete from, or otherwise modify the contents  */
/*          of this include file.                                         */
/**************************************************************************/

#include <userint.h>

#ifdef __cplusplus
    extern "C" {
#endif

     /* Panels and Controls: */

#define  FRQ_PANEL                        1       /* callback function: OnFrequencyPanel */
#define  FRQ_PANEL_IDC_SWITCH_PANEL       2       /* control type: binary, callback function: OnSwitchPanelCB */
#define  FRQ_PANEL_IDC_GRAPH_FIL_DATA     3       /* control type: graph, callback function: (none) */
#define  FRQ_PANEL_IDC_GRAPH_FISPEC_DATA  4       /* control type: graph, callback function: (none) */
#define  FRQ_PANEL_IDC_GRAPH_SPEC_DATA    5       /* control type: graph, callback function: (none) */
#define  FRQ_PANEL_IDC_GRAPH_RAW_DATA     6       /* control type: graph, callback function: (none) */
#define  FRQ_PANEL_IDC_NUM_POWERPEAK      7       /* control type: numeric, callback function: (none) */
#define  FRQ_PANEL_IDC_NUM_SECUNDA_2      8       /* control type: numeric, callback function: (none) */
#define  FRQ_PANEL_IDC_NUM_SECUNDA        9       /* control type: numeric, callback function: (none) */
#define  FRQ_PANEL_IDC_NUM_N              10      /* control type: numeric, callback function: (none) */
#define  FRQ_PANEL_IDC_NUM_FREQPEAK       11      /* control type: numeric, callback function: (none) */
#define  FRQ_PANEL_IDC_GETSPECTRU         12      /* control type: command, callback function: OnGetSpectru */
#define  FRQ_PANEL_IDC_RING_FILTER_TYPE   13      /* control type: ring, callback function: (none) */
#define  FRQ_PANEL_IDC_RING_FILTER        14      /* control type: ring, callback function: (none) */
#define  FRQ_PANEL_IDC_RING_WINDOW        15      /* control type: ring, callback function: (none) */

#define  MAIN_PANEL                       2       /* callback function: OnMainPanel */
#define  MAIN_PANEL_IDC_SWITCH_PANEL      2       /* control type: binary, callback function: OnSwitchPanelCB */
#define  MAIN_PANEL_IDC_GRAPH_FILTER_DATA 3       /* control type: graph, callback function: (none) */
#define  MAIN_PANEL_IDC_GRAPH_RAW_DATA    4       /* control type: graph, callback function: OnGraphRaw */
#define  MAIN_PANEL_LOADBUTTON            5       /* control type: command, callback function: OnLoadButtonCB */
#define  MAIN_PANEL_NUM_ZEROCROSS         6       /* control type: numeric, callback function: (none) */
#define  MAIN_PANEL_NUM_DISPERSION        7       /* control type: numeric, callback function: (none) */
#define  MAIN_PANEL_NUM_MEDIAN            8       /* control type: numeric, callback function: (none) */
#define  MAIN_PANEL_NUM_MAX               9       /* control type: numeric, callback function: (none) */
#define  MAIN_PANEL_NUM_MEAN              10      /* control type: numeric, callback function: (none) */
#define  MAIN_PANEL_NUM_MIN               11      /* control type: numeric, callback function: (none) */
#define  MAIN_PANEL_RING_FILTER           12      /* control type: ring, callback function: OnFilterSelect */
#define  MAIN_PANEL_NUM_ALPHA             13      /* control type: numeric, callback function: (none) */
#define  MAIN_PANEL_NUM_STOP              14      /* control type: numeric, callback function: OnNumSecunde */
#define  MAIN_PANEL_NUM_START             15      /* control type: numeric, callback function: OnNumSecunde */
#define  MAIN_PANEL_NUM_N                 16      /* control type: numeric, callback function: (none) */
#define  MAIN_PANEL_BUTTON_NEXT           17      /* control type: command, callback function: OnNextPrevCB */
#define  MAIN_PANEL_BUTTON_PREV           18      /* control type: command, callback function: OnNextPrevCB */
#define  MAIN_PANEL_BUTTON_APPLY          19      /* control type: command, callback function: OnApplyCB */
#define  MAIN_PANEL_TEXTMSG               20      /* control type: textMsg, callback function: (none) */


     /* Control Arrays: */

          /* (no control arrays in the resource file) */


     /* Menu Bars, Menus, and Menu Items: */

          /* (no menu bars in the resource file) */


     /* Callback Prototypes: */

int  CVICALLBACK OnApplyCB(int panel, int control, int event, void *callbackData, int eventData1, int eventData2);
int  CVICALLBACK OnFilterSelect(int panel, int control, int event, void *callbackData, int eventData1, int eventData2);
int  CVICALLBACK OnFrequencyPanel(int panel, int event, void *callbackData, int eventData1, int eventData2);
int  CVICALLBACK OnGetSpectru(int panel, int control, int event, void *callbackData, int eventData1, int eventData2);
int  CVICALLBACK OnGraphRaw(int panel, int control, int event, void *callbackData, int eventData1, int eventData2);
int  CVICALLBACK OnLoadButtonCB(int panel, int control, int event, void *callbackData, int eventData1, int eventData2);
int  CVICALLBACK OnMainPanel(int panel, int event, void *callbackData, int eventData1, int eventData2);
int  CVICALLBACK OnNextPrevCB(int panel, int control, int event, void *callbackData, int eventData1, int eventData2);
int  CVICALLBACK OnNumSecunde(int panel, int control, int event, void *callbackData, int eventData1, int eventData2);
int  CVICALLBACK OnSwitchPanelCB(int panel, int control, int event, void *callbackData, int eventData1, int eventData2);


#ifdef __cplusplus
    }
#endif