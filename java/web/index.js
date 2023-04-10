function validate() {
    var email = document.getElementById("input_email")
    var password = document.getElementById("input_pw")
    var role = document.getElementsByName("user_role")

    if (email.value == '') {
        alert("Email cannot be empty!")
        return false
    }

    if (password.value == '') {
        alert("Password cannot be empty!")
        return false
    }

    if (role.value == '') {
        alert("Please choose a role!")
        return false
    }

    return true
}