class Produs{
    constructor(id, nume, cantitate)
    {
        this.id = id;
        this.nume = nume;
        this.cantitate = cantitate;
    }
}
var id = 1;
var worker = new Worker('js/worker.js');

function addToList(){
    
    let nume = document.getElementById("nume_produs").value;
    let cantitate = document.getElementById("cantitate_produs").value;
    let obiect = new Produs(id, nume, cantitate );
    //alert(obiect.nume + " Hey");
    id = id + 1;

    localStorage.setItem("id" + obiect.id, obiect.id);
    localStorage.setItem("nume" + obiect.id, obiect.nume);
    localStorage.setItem("cantitate" + obiect.id, obiect.cantitate);

    worker.postMessage([obiect.id, obiect.nume, obiect.cantitate]);
/*
    if (document.getElementById("write_me_here"))
    document.getElementById("write_me_here").innerHTML = "";
    for (var i = 1; i<id; i++)
     {
         if (document.getElementById("write_me_here"))
            document.getElementById("write_me_here").innerHTML += localStorage.getItem("id" + i) + " " + localStorage.getItem("nume" + i) + " " + localStorage.getItem("cantitate" + i) + "\r\n" + "\r\n";

     }
*/
}

function addToTable(){
    var deCreat;
    var myTr, myTd, myTextNode;
    var index1;
    worker.onmessage = function(e) {
        deCreat = e.data;
        console.log('Message received from worker');

        myTr = document.createElement("TR");
        myTr.setAttribute("id", "cumparaturi_tr"+deCreat[0]);
        if (document.getElementById("cumparaturi_tabel"))
            document.getElementById("cumparaturi_tabel").appendChild(myTr);

        for (index1 = 0; index1 <3; index1++)
        {
            myTd = document.createElement("TD");
            myTextNode = document.createTextNode(deCreat[index1]);
            myTd.appendChild(myTextNode);
            if (document.getElementById("cumparaturi_tabel"))
                document.getElementById("cumparaturi_tr"+deCreat[0]).appendChild(myTd);
           
        }
        

    }
}