function receiving_calendar_parameters(text) {
    let param = (new URL(document.location)).searchParams;
    return param.get(text)
}

function getId(id) {
    return document.getElementById(id);
}

async function load_date() {
    if (receiving_calendar_parameters('id') == null){
        let resp = await fetch('http://localhost:8080/personal_clas',
            {
                method: 'POST',
            }).then(res => res.json())
            .then(res=>creatMassiv(res));
    }
    else {
        const data = new FormData();
        data.append("id", receiving_calendar_parameters('id'));//test in 1
        let response = await fetch('http://localhost:8080/personal_classes',
            {
                method: 'POST',
                body: data,
            }).then(res => res.json())
            .then(res => creatMassiv(res));
    }
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

load_date();