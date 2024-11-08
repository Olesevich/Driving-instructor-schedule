function getId(id) {
    return document.getElementById(id);
}


async function load_date() {
    const data = new FormData()
    data.append('day', receiving_calendar_parameters("day"))
    data.append('month', receiving_calendar_parameters("month"))
    data.append('year', receiving_calendar_parameters("year"))
    let response = await fetch('http://localhost:8080/load_date',
        {
            method: 'POST',
            body: data,
        }).then(res => res.json())
        .then(res => displaying_values_from_an_array(res));
}

function state_of_buttons_and_text(response){
    let mas = [];
    for (let c in response){
        let v = response[c];
        mas.push(c,v);
    }
    return mas;
}

function displaying_values_from_an_array(respone){
    var massiv_post_familyname_time = state_of_buttons_and_text(respone);
    pushButton(massiv_post_familyname_time);
}


function but(but, but_text) {//красит и блокирует времена которые заняты в базе данных
    but_text.style.background = 'red';//меняет фон на инпуте
    but_text.value = "Занято";//меняет текст в инпуте
    but.disabled = true;//делает кнопку не активной
}

function event_button(i) {
    switch (i){
        case '8':
            but(getId('btn8_00'),getId('input8_00'));
            break;
        case '9':
            but(getId('btn9_00'),getId('input9_00'));
            break;
        case '10':
            but(getId('btn10_00'),getId('input10_00'));
            break;
        case '11':
            but(getId('btn11_00'),getId('input11_00'));
            break;
        case '12':
            but(getId('btn12_00'),getId('input12_00'));
            break;
        case '13':
            but(getId('btn13_00'),getId('input13_00'));
            break;
        case '14':
            but(getId('btn14_00'),getId('input14_00'));
            break;
        case '15':
            but(getId('btn15_00'),getId('input15_00'));
            break;
        case '16':
            but(getId('btn16_00'),getId('input16_00'));
            break;
    }
}

function state_of_buttons_and_lines(massiv_bd) {
    for (let i = 0; i < massiv_bd.length; i++) {
        if (i % 2 === 0 ){
            event_button(massiv_bd[i]);
        }
        else {
            //здесь будет код для человека который зашел под админом, и вместо занято написано кем занято, здесь получение с
            //массива фамилии учащихся
        }
    }
}

//функция которая после нажатия кнопки меняет цвет у текста, а так же делает не активную кнопку
function eventPushButton(but, but_text, button_text_value){
    but.addEventListener('click',async () => {
        const data = new FormData()
        data.append('time', button_text_value)
        let response = await fetch('http://localhost:8080/push_time',
            {
                method: 'POST',
                body: data,
            })
        but_text.style.background = 'red';//меняет фон на инпуте
        but_text.value = "Занято";//меняет текст в инпуте
        but.disabled = true;//делает кнопку не активной
    })
}

function receiving_calendar_parameters(text) {
    let param = (new URL(document.location)).searchParams;
    return param.get(text)
}

//функция действия на все кнопки и тексты
function pushButton(massiv_bd){
    state_of_buttons_and_lines(massiv_bd);
    eventPushButton(getId('btn8_00'),getId('input8_00'),"8");
    eventPushButton(getId('btn9_00'),getId('input9_00'),"9");
    eventPushButton(getId('btn10_00'),getId('input10_00'),"10");
    eventPushButton(getId('btn11_00'),getId('input11_00'),"11");
    eventPushButton(getId('btn12_00'),getId('input12_00'),"12");
    eventPushButton(getId('btn13_00'),getId('input13_00'),"13");
    eventPushButton(getId('btn14_00'),getId('input14_00'),"14");
    eventPushButton(getId('btn15_00'),getId('input15_00'),"15");
    eventPushButton(getId('btn16_00'),getId('input16_00'),"16");
}


load_date();

// pushButton();

