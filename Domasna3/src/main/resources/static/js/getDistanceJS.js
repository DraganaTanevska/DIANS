function Prefrli(id){
    x =document.getElementById("forma")
    y=document.getElementById("Radio")
    yy=id.value
    kopce=document.getElementById("kopce")
    kopce.disabled=false
    kopce.value="Calculate"
    kopce.style.backgroundColor="red"
    kopce.style.color="white"
    console.log(id.value)
    x.innerHTML = "<input type='hidden' name='id' value="+yy+">"
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
    document.getElementById("demo").innerHTML ="<input type='hidden' name='latitude' value="+ position.coords.latitude +"/>" + "<br><input type='hidden' name='longitude' value="+position.coords.longitude+">"
}