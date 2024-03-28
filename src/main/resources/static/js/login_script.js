document.addEventListener('DOMContentLoaded', function () {
    var loginForm = document.getElementById('login-form');

    loginForm.addEventListener('submit', function (event) {
        event.preventDefault(); // Предотвращаем стандартную отправку формы

        var username = loginForm.querySelector('input[name="username"]').value;
        var password = loginForm.querySelector('input[name="password"]').value;

        // Создаем объект с данными для отправки
        var loginData = {
            username: username,
            password: password
        };

        // Отправляем данные формы с помощью fetch
        fetch('/api/v1/auth/access_token', { // Указываем URL напрямую
            method: 'POST',
            headers: {
                'Content-Type': 'application/json' // Указываем тип содержимого
            },
            body: JSON.stringify(loginData) // Преобразуем данные в строку JSON
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok: ' + response.statusText);
                }
                return response.json();
            })
            .then(data => {
                console.log(data); // Обработка данных, полученных в ответ
            })
            .catch(error => {
                console.error('Ошибка:', error);
            });
    });
});