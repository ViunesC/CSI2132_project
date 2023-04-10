function validate() {
    var name = document.getElementById("name")
    var email = document.getElementById("email")
    var pw1 = document.getElementById("pw1")
    var pw2 = document.getElementById("pw2")

    if (name.value == '') {
        alert("Name cannot be empty!")
        return false
    }

    if (email.value == '') {
        alert("Email cannot be empty!")
        return false
    }

    if (pw1.value == '' || pw2.value == '') {
        alert("Password cannot be empty!")
        return false
    } else if (pw1.value != pw2.value) {
        alert("Two passwords does not match. Try again!")
        return false
    }

    return true
}