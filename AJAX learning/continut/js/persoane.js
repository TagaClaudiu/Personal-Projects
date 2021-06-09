
function incarcaPersoane(){
    //dintr-un motiv sau altul, se apeleaza de 4 ori functia
    
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
        creeazaTabel(this);
        }
    };
    
    xhttp.open("GET", "resources/persoane.xml", true);
    xhttp.send();
    
}


function creeazaTabel(xml){
    
    var xmlDoc = xml.responseXML;
    var index1;
    var index2;
    var temp;
    var nodeName;
    var myTd, myTr, myTh, myTextNode;
    var myTable = document.createElement("TABLE");
    myTable.setAttribute("id", "persoane_tabel");
    if (document.getElementById("persoane_section"))
    document.getElementById("persoane_section").appendChild(myTable);

 //   if (document.getElementById("persoane_tabel"))
  //      {
   //         document.getElementById("persoane_tabel").style.border = "2px";
  //      }

    //4 tot cu th-urile
    for(index1 = 1; index1<=4; index1++)
    {
        myTr = document.createElement("TR");
        myTr.setAttribute("id", "persoane_tr"+index1);
        if (document.getElementById("persoane_tabel"))
        document.getElementById("persoane_tabel").appendChild(myTr);
        
       // 
        if (index1 === 1)
        {
            myTh = document.createElement("TH");
            myTextNode = document.createTextNode("nume");
            myTh.appendChild(myTextNode);
            if (document.getElementById("persoane_tabel"))
                    document.getElementById("persoane_tr"+index1).appendChild(myTh);

            myTh = document.createElement("TH");
            myTextNode = document.createTextNode("prenume");
            myTh.appendChild(myTextNode);
            if (document.getElementById("persoane_tabel"))
                 document.getElementById("persoane_tr"+index1).appendChild(myTh);

            myTh = document.createElement("TH");
            myTextNode = document.createTextNode("varsta");
            myTh.appendChild(myTextNode);
            if (document.getElementById("persoane_tabel"))
                document.getElementById("persoane_tr"+index1).appendChild(myTh);

            myTh = document.createElement("TH");
            myTextNode = document.createTextNode("adresa");
            myTh.appendChild(myTextNode);
            if (document.getElementById("persoane_tabel"))
                document.getElementById("persoane_tr"+index1).appendChild(myTh);

            myTh = document.createElement("TH");
            myTextNode = document.createTextNode("profesie");
            myTh.appendChild(myTextNode);
            if (document.getElementById("persoane_tabel"))
                document.getElementById("persoane_tr"+index1).appendChild(myTh);

            myTh = document.createElement("TH");
            myTextNode = document.createTextNode("sex");
            myTh.appendChild(myTextNode);
            if (document.getElementById("persoane_tabel"))
                document.getElementById("persoane_tr"+index1).appendChild(myTh);  
        }
        else
        {
            for(index2 = 1; index2<=6; index2++)
            { 
                if (index2 === 4)
                {
                    temp = "Strada ";
                    temp += xmlDoc.getElementsByTagName("strada")[index1-2].childNodes[0].nodeValue;
                    temp += ", numarul " + xmlDoc.getElementsByTagName("numar")[index1-2].childNodes[0].nodeValue;
                    temp += ", localitatea " + xmlDoc.getElementsByTagName("localitate")[index1-2].childNodes[0].nodeValue;
                    temp += ", tara " + xmlDoc.getElementsByTagName("tara")[index1-2].childNodes[0].nodeValue;
                }
                else
                {
                    switch(index2)
                    {
                    case 1: nodeName = "nume"; break;
                    case 2: nodeName = "prenume"; break;
                    case 3: nodeName = "varsta"; break;
                    case 5: nodeName = "profesie"; break;
                    case 6: nodeName = "sex"; break;
                    }
                    temp = xmlDoc.getElementsByTagName(nodeName)[index1-2].childNodes[0].nodeValue;
                }

                    myTd = document.createElement("TD");
                    myTextNode = document.createTextNode(temp);
                    myTd.appendChild(myTextNode);
                    if (document.getElementById("persoane_tabel"))
                        document.getElementById("persoane_tr"+index1).appendChild(myTd);
                
               

            }
        }
    }

    
}
