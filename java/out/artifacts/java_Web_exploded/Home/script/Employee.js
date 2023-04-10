function validate() {
    var name = document.getElementById("customer_name")
    var phone = document.getElementById("customer_phone")

    if (name.value == '') {
        alert("Please enter customer name")
        return false
    }

    if (phone.value == '') {
        alert("Please enter customer phone")
        return false
    }
}