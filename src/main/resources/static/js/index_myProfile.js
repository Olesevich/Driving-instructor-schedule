function getId(id) {
    return document.getElementById(id);
}

async function load_date() {
    let response = await fetch('http://localhost:8080/personal_classes',
        {
            method: 'POST'
        }).then(res => res.json())
        .then(res=>creatMassiv(res));

}

function creatMassiv(response) {
    var massiv_post_data = state_of_text(response);
    change_text_input(massiv_post_data);
    console.log(massiv_post_data);
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