function Prefrli(id)
{
    x =document.getElementById("forma")
    y=document.getElementById("Radio")
    yy=id.value
    kopce=document.getElementById("kopce")
    kopce.disabled=false
    kopce.value="Calculate"
    console.log(id.value)
    x.innerHTML = "<input type='hidden' name='idPlace' value="+yy+">"
}

var xxx = document.getElementById("demo");

function getLocation() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(showPosition);
    } else {
        xxx.innerHTML = "Geolocation is not supported by this browser.";
    }
}

function showPosition(position) {
    xxx.innerHTML = "<input type='hidden' name='latitude' value="+ position.coords.latitude +">"+
        "<br><input type='hidden' name='longitude' value="+ position.coords.longitude+">"
}