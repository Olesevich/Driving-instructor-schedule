function getId(id) {
    return document.getElementById(id);
}

async function load_date() {
    let response = await fetch('http://localhost:8080/home/history',
        {
            method: 'POST'
        }).then(res => res.json())
        .then(res=>creat_table(res));

}

function creat_table(response) {
    console.log(response);
    var html;
    html = '<table>';
    html += '<tr> ' +
        '<td>Логин</td> ' +
        '<td>Фамилия</td> ' +
        '<td>Имя</td> ' +
        '<td>Отчество</td> ' +
        '<td>Номер группы</td> ' +
        '<td></td> ' +
        '</tr>'

    var mas_post_data = post_massiv(response);
    for (var i = 0; i < mas_post_data.length; i++) {
        var mas_string = post_massiv(mas_post_data[i]);
        if (mas_string[0] == '1') {
            continue;
        }
            else{
            html += '<tr> ' +
                '<td><a href="myprof?id=' + mas_string[0] +'">' + mas_string[1] + '</td>' +
                '<td>' + mas_string[2] + '</td>' +
                '<td>' + mas_string[3] + '</td>' +
                '<td>' + mas_string[4] + '</td>' +
                '<td>' + mas_string[5] + '</td>' +
                '<td><button id="'+ i +'" onclick="clikButton(' + mas_string[0] + ')">Удалить</button></td>' +
                '</tr>';
        }
    }

    html += '</table>';

    document.getElementById('divHistory').innerHTML = html;
}

async function clikButton(int) {
    const data = new FormData();
    data.append("id", int);
    let response = await fetch('http://localhost:8080/home/history/remove_user',
            {
                method: 'POST',
                body: data,
            })
        location.reload();
    // }
}

function post_massiv(response){
    let mas = [];
    for (let x in response) {//получение массива без ключа, только тело
        mas.push(response[x]);
    }
    return mas;
}

load_date();