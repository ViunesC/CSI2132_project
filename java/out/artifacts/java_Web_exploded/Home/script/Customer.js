function validate() {
    var startD = document.getElementById("start_date")
    var endD = document.getElementById("end_date")
    var area = document.getElementById("area")

    var today = new Date()

    if (startD.value == '' || endD.value == '') {
        alert("Please enter valid date")
        return false
    }

    if (new Date(startD.value).getTime() < today.getTime()) {
        alert("start date must no earlier than current time")
        return false
    }

    if (new Date(startD.value).getTime() > new Date(endD.value).getTime()) {
        alert("Start date must be earlier than end date")
        return false
    }

    if (area.value == '') {
        alert("Please enter area")
        return false
    }

    return true
}