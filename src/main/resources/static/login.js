function validate(){
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;

    console.log(username);
    console.log(password);



}

function validateMyForm()
{
    let username = document.getElementById("email").value;
    let password = document.getElementById("password").value;
    let re = new RegExp("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");

    if(!re.test(username))
    {
        alert("Please enter a valid email address");

        return false;
    }
    if(password === "") {
        alert("Please enter a password");
        return false;
    }

    alert("You have successfully been logged in.");
    return true;
}
