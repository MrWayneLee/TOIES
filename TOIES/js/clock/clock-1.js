let hourEle = document.getElementsByClassName("hour")[0]
let minuteEle = document.getElementsByClassName("minute")[0]
let secondEle = document.getElementsByClassName("second")[0]
var hourDeg = (new Date().getHours() - 12) / 12 * 360 + new Date().getMinutes() / 60 * 30
var minuteDeg = new Date().getMinutes() / 60 * 360 + new Date().getSeconds() / 60 * 6
var secondDeg = new Date().getSeconds() / 60 * 360
setTime(hourDeg, minuteDeg, secondDeg)
var event = setInterval(() => {
    hourDeg = (new Date().getHours() - 12) / 12 * 360 + new Date().getMinutes() / 60 * 30
    minuteDeg = new Date().getMinutes() / 60 * 360 + new Date().getSeconds() / 60 * 6
    secondDeg = new Date().getSeconds() / 60 * 360
    setTime(hourDeg, minuteDeg, secondDeg)
}, 1000)
function setTime(h, m, s) {
    hourEle.style.transform = "rotate(" + h + "deg)"
    minuteEle.style.transform = "rotate(" + m + "deg)"
    secondEle.style.transform = "rotate(" + s + "deg)"
}