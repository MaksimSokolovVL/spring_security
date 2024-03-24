function upInfoUser(id) {

    document.getElementById("updateButton").style.display = "block";
    document.getElementById("deleteButton").style.display = "none";

    const url = '/admin/user/' + id

    $.getJSON(url, function (data) {
        let elemId = document.getElementById('userId_mod');
        elemId.value = data.id;
        elemId.classList.add("info_readonly");
        // $('#userId_mod').prop('readonly', true);
        $('#userId_mod').prop('disabled', true);

        let elemUsername = document.getElementById('login_mod');
        elemUsername.value = data.value;
        $('#login_mod').prop('disabled', false);

        let elemName = document.getElementById('name_mod');
        elemName.value = data.name;
        $('#name_mod').prop('disabled', false);

        let elementSurname = document.getElementById('path_mod');
        elementSurname.value = data.path;
        $('#path_mod').prop('disabled', false);

        let elementAge = document.getElementById('age_mod');
        elementAge.value = data.age;
        $('#age_mod').prop('disabled', false);

        $('#password_mod').css("visibility", "visible");
        $('#label_password_mod').css("visibility", "visible")

        let elementActive = document.getElementById('act_mod');
        elementActive.checked = data.active;
        elementActive.value = elementActive.checked;

        $('#roles_mod').prop('disabled', false);
        $('#act_mod').prop('disabled', false);
    });
}

const csrfToken = $("meta[name='_csrf']").attr("content");
const csrfHeader = $("meta[name='_csrf_header']").attr("content");

function saveUpUser() {

    // console.log(csrfToken)
    // console.log(csrfHeader)

    const url = '/admin/user';
    const userId = document.getElementById('userId_mod').value;
    const login = document.getElementById('login_mod').value;
    const name = document.getElementById('name_mod').value;
    const path = document.getElementById('path_mod').value;
    const age = document.getElementById('age_mod').value;
    const password = document.getElementById('password_mod').value;
    const active = document.getElementById('act_mod').checked;
    const roles = Array.from(document.getElementById('roles_mod').selectedOptions).map(option => option.value);

    console.log(userId, login, name, path, age, password,  active, roles)

    const data = {
        "id": userId,
        "value": login,
        "name": name,
        "path": path,
        "age": age,
        "password": password,
        "active": active,
        "roles": roles
    };

    // console.log(data)

    $.ajax({
        type: "PUT",
        url: url,
        contentType: "application/json",
        beforeSend: function (xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
        data: JSON.stringify(data),
        success: function (response) {
            console.log(response);
            location.reload();
        },
        error: function (error) {
            console.log(error);
            alert("Failed to update user.");
        }
    });
}

let _user_id;

function deleteInfo(id) {
    const url = '/admin/user/' + id
    document.getElementById("deleteButton").style.display = "block";
    document.getElementById("updateButton").style.display = "none";

    _user_id = id

    $.getJSON(url, function (data) {
        let elemId = document.getElementById('userId_mod');
        elemId.value = data.id;
        $('#userId_mod').prop('disabled', true);

        let elemUsername = document.getElementById('login_mod');
        elemUsername.value = data.value;
        $('#login_mod').prop('disabled', true);

        let elemName = document.getElementById('name_mod');
        elemName.value = data.name;
        $('#name_mod').prop('disabled', true);

        let elementSurname = document.getElementById('path_mod');
        elementSurname.value = data.path;
        $('#path_mod').prop('disabled', true);

        let elementAge = document.getElementById('age_mod');
        elementAge.value = data.age;
        $('#age_mod').prop('disabled', true)

        $('#password_mod').css("visibility", "hidden");
        $('#label_password_mod').css("visibility", "hidden")

        let elementActive = document.getElementById('act_mod');
        elementActive.checked = data.active;
        elementActive.value = elementActive.checked;

        $('#roles_mod').prop('disabled', true);
        $('#act_mod').prop('disabled', true);

    });
}

function deleteUser() {

    const url = '/admin/user/' + _user_id

    $.ajax({
        url: url,
        type: 'DELETE',
        beforeSend: function (xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
        success: function (result) {
            location.reload()
        }
    });

}













