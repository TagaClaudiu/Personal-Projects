function getTime(){
    if (document.getElementById("js_time") != null)
        document.getElementById("js_time").innerHTML = "Data si ora curenta este: " + Date();
    if (document.getElementById("clock_a") != null)
        document.getElementById("clock_a").innerHTML = "";
}

function showTime()
{
    window.setInterval(getTime, 1000 );
    calculateTime();
}

function calculateTime()
{
    let myDate = new Date();
    let day = myDate.getDay();
    let hour = myDate.getHours();

    if (day <= 4)
    {
        if (hour < 14)
        {
            if (document.getElementById("js_calcTime") != null)
            document.getElementById("js_calcTime").innerHTML = "Nu suntem deschisi inca!";
            document.getElementById("js_calcTime").style.color = "red";
        }
        else if (hour > 22)
        {
            if (document.getElementById("js_calcTime") != null)
            document.getElementById("js_calcTime").innerHTML = "Pubul deja s-a inchis, incearca maine!";
            document.getElementById("js_calcTime").style.color = "red";
        }
        else
        {
            if (document.getElementById("js_calcTime") != null)
            document.getElementById("js_calcTime").innerHTML = "Pubul este deschis! Esti binevenit sa ne vizitezi!";
            document.getElementById("js_calcTime").style.color = "green";
        }
    }
    else
    {
        if (hour < 15)
        {
            if (document.getElementById("js_calcTime") != null)
            document.getElementById("js_calcTime").innerHTML = "Nu suntem deschisi inca!";
            document.getElementById("js_calcTime").style.color = "red";
        }
        else if (hour > 23)
        {
            if (document.getElementById("js_calcTime") != null)
            document.getElementById("js_calcTime").innerHTML = "Pubul deja s-a inchis, incearca maine!";
            document.getElementById("js_calcTime").style.color = "red";
        }
        else
        {
            if (document.getElementById("js_calcTime") != null)
            document.getElementById("js_calcTime").innerHTML = "Pubul este deschis! Esti binevenit sa ne vizitezi!";
            document.getElementById("js_calcTime").style.color = "green";
        }
    }
}

function showBusinessPlan()
{
    var plan = document.createElement("iframe");
    plan.setAttribute("width", "1000");
    plan.setAttribute("height", "1000");
    plan.setAttribute("src", "../documents/PlanAfaceri.pdf");

    if (document.getElementById("plan_afaceri") != null)
        document.getElementById("plan_afaceri").appendChild(plan);
}
