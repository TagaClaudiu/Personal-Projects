function getDate(){
    if (document.getElementById("js_date") != null)
        document.getElementById("js_date").innerHTML = Date();
}

function showLocation(position){
    cords = position.coords;
    if(document.getElementById("js_location"))
    document.getElementById("js_location").innerHTML = "Latitudine: " + cords.latitude + 
    " Longitudine: " + cords.longitude;
}

function getInfo(){
    
    window.setInterval(getDate, 1000 );

    if(document.getElementById("js_url")){
        document.getElementById("js_url").innerHTML = window.location.href;
    }
    
    if (navigator.geolocation)
    {
        navigator.geolocation.getCurrentPosition(showLocation);
    }
    else{
        document.getElementById("js_location").innerHTML = "Geolocation not supported.";
    }
    if(document.getElementById("js_bname"))
    document.getElementById("js_bname").innerHTML = navigator.appName;
    if(document.getElementById("js_bver"))
    document.getElementById("js_bver").innerHTML = navigator.appVersion;
    if(document.getElementById("js_plat"))
    document.getElementById("js_plat").innerHTML = navigator.platform;

}

function lotoJoc(){
    var numere = [];
    var numereJucator = [];
    var nrCorecte = 0;
    var i;
    if (document.getElementById("raspunsuri"))
        {
            document.getElementById("raspunsuri").innerHTML = "Numerele castigatoare sunt: ";
            document.getElementById("raspunsuri").setAttribute("class", "beauty");
        }
    if (document.getElementById("raspunsuri2"))
        {
            document.getElementById("raspunsuri2").innerHTML = "";
            document.getElementById("raspunsuri2").setAttribute("class", "beauty");
        }
    
    for (i = 1; i<=8; i++)
    {
        if (document.getElementById("loto"+i))
            numereJucator[i-1] = document.getElementById("loto"+i).value;

        numere[i-1] = ((Math.floor(Math.random()*255)).toString(16)).toUpperCase();
        
        document.getElementById("raspunsuri").innerHTML += numere[i-1] + " ";

           if (numereJucator[i-1] == numere[i-1] )
            {
                nrCorecte++;
            }
    }
    if (document.getElementById("raspunsuri2"))
    {
        switch(nrCorecte){
            case 0: document.getElementById("raspunsuri2").innerHTML = "Nu ati nimerit niciun numar. Mai incercati! ";
                    break;
            case 1: document.getElementById("raspunsuri2").innerHTML = "Ati nimerit " + nrCorecte + " numar! Felicitari!";
                    break;
            default: document.getElementById("raspunsuri2").innerHTML = "Ati nimerit " + nrCorecte + " numere! Felicitari!";
        }
    }
        
}

function getMousePos(canvas, event) {
    var rect = canvas.getBoundingClientRect();
    return {
      x: event.clientX - rect.left,
      y: event.clientY - rect.top
    };
}

var clicks = 0;
var pos1, pos2;

function desenCanvas(event)
{
    var myCanvas;

    if (document.getElementById("culoare_contur"))
         var borderColor = document.getElementById("culoare_contur").value;
    if (document.getElementById("culoare_umplere"))     
        var fillColor = document.getElementById("culoare_umplere").value;

    if (document.getElementById("js_canvas"))
        {
            myCanvas = document.getElementById("js_canvas");
            if (myCanvas.getContext("2d"))
            {
                /** @type {CanvasRenderingContext2D} */
                var ctx = myCanvas.getContext("2d");

                if (clicks == 0)
                {
                    pos1 = getMousePos(myCanvas, event);
                    clicks++;
                }
                else if (clicks == 1)
                {
                    pos2 = getMousePos(myCanvas, event);
                    pos2.x -= pos1.x;
                    pos2.y -= pos1.y;
                    if (pos1 && pos2)
                    {
                    
                        ctx.beginPath();
                        ctx.rect(pos1.x, pos1.y, pos2.x, pos2.y);
                        ctx.fillStyle = fillColor;
                        ctx.fill();
                        ctx.beginPath();
                        ctx.rect(pos1.x, pos1.y, pos2.x, pos2.y);
                        ctx.strokeStyle = borderColor;
                        ctx.stroke();
                    }
                clicks++;
                }
                else
                {
                    ctx.clearRect(0, 0, myCanvas.width, myCanvas.height);
                    clicks = 0;
                }            
            }
        }
}


function schimbaContinut(continut, jsFisier, jsFunctie){
    // alert("continut");
     var xhttp = new XMLHttpRequest();
   xhttp.onreadystatechange = function() {
     if (this.readyState == 4 && this.status == 200) {
        // alert("alertif");
       document.getElementById("continut").innerHTML = this.responseText;
       if (jsFisier) {
            var elementScript = document.createElement('script');
            elementScript.onload = function () {
            console.log("hello");
            if (jsFunctie) {
                window[jsFunctie]();
                }
            };
            elementScript.src = jsFisier;
            document.head.appendChild(elementScript);
        }
        else {
            if (jsFunctie) {
            window[jsFunctie]();
            }
        }
    }  
   };
   var nume = continut + '.html';
   //alert(nume);
   xhttp.open("GET", nume, true);
   xhttp.send();
   
 }
