document.addEventListener('DOMContentLoaded', function () {
let date = document.getElementById("register").value;
let year=date.substring(0,4);
let month=date.substring(4,6);
let day=date.substring(6,8);
let hh=date.substring(8,10);
let mm=date.substring(10,12);
let ss=date.substring(12,14);
let str = year+"/"+month+"/"+day+" "+hh+":"+mm+":"+ss;
document.getElementById("register").value=str;
});