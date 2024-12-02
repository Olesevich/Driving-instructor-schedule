function receiving_calendar_parameters(text) {
    let param = (new URL(document.location)).searchParams;
    return param.get(text)
}

function getId(id) {
    return document.getElementById(id);
}

async function load_date() {
    let respu = await fetch('http://localhost:8080/role',//для опроса админ или нет, если да то появит кнопк
        {
            method: 'POST',
        }).then(respo => respo.text())
        .then(respo=>button_history_admin(respo));

    if (receiving_calendar_parameters('id') == null){//для захода на личнуб карточку
        let resp = await fetch('http://localhost:8080/personal_clas',
            {
                method: 'POST',
            }).then(res => res.json())
            .then(res=>creatMassiv(res));
    }
    else {
        const data = new FormData();
        data.append("id", receiving_calendar_parameters('id'));
        let response = await fetch('http://localhost:8080/personal_classes',//для заход. на лич карту,то через историю
            {
                method: 'POST',
                body: data,
            }).then(res => res.json())
            .then(res => creatMassiv(res));
    }
    // button_history_admin(1);
}

function creatMassiv(response) {
    var massiv_post_data = state_of_text(response);
    change_text_input(massiv_post_data);
}

function state_of_text(response){
    let mas = [];
    for (let x in response) {//получение массива без ключа, только тело
        mas.push(response[x]);
    }
    return mas;
}

function change_text_input(massiv_post_data){
    for (let i = 0; i < massiv_post_data.length; i++) {
        getId('input_' + i).value = massiv_post_data[i];
    }
}

function button_history_admin(a){
    if (a == "ROLE_ADMIN") {
        var html;

        html = '<button id="button_history" onclick="document.location.href=\'../home/history\'" id="but_myProfile"\n' +
            'type="button_history">Список пользователей</button>';
        document.getElementById('divDay').innerHTML = html;
    }
}


load_date();