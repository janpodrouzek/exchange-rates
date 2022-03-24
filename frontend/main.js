const API_BASE = 'http://localhost:8080/api/v1';
const FLAG_IMG_TEMPLATE = 'https://flagcdn.com/24x18/{cc}.png'

document.addEventListener('load', fetchData(false));

function fetchData(useDb = true) {
    fetch(API_BASE + '/rates/exchangerates?usedb=' + useDb.toString())
        .then(response => {
            if (!response.ok) {
                throw new Error();
            }

            return response.json();
        })
        .then(data => renderData(data))
        .catch(() => {
            alert("Chyba při načítání kurzů");
        })
}

function renderData(data) {
    let tableBody = document.querySelector('#ratesTable tbody');

    while(tableBody.firstChild) {
        tableBody.firstChild.remove();
    }

    Array.from(data).forEach(currency => {
        let row = document.createElement('tr');
        row.appendChild(createFlag(currency));
        row.appendChild(createAmount(currency));
        row.appendChild(createBuy(currency));
        row.appendChild(createSell(currency));
        row.appendChild(createMid(currency));
        row.appendChild(createCnbMid(currency));
        row.appendChild(createMove(currency));
        tableBody.appendChild(row);
    });
}

function createTableDataContainer() {
    return document.createElement('td');
}

function createFlag(currency) {
    let container = createTableDataContainer();
    let flag = document.createElement('img');
    flag.classList.add('pe-2');
    flag.src = FLAG_IMG_TEMPLATE.replace('{cc}', currency.shortName.substring(0, 2).toLowerCase())
    container.append(flag);
    let currencyCode = document.createElement('span');
    currencyCode.textContent = currency.shortName;
    container.append(currencyCode);

    return container;
}

function createAmount(currency) {
    let container = createTableDataContainer();
    container.textContent = currency.amount;

    return container;
}

function createBuy(currency) {
    let container = createTableDataContainer();
    container.textContent = currency.currBuy;

    return container;
}

function createSell(currency) {
    let container = createTableDataContainer();
    container.textContent = currency.currSell;

    return container;
}

function createMid(currency) {
    let container = createTableDataContainer();
    container.textContent = currency.currMid;

    return container;
}

function createCnbMid(currency) {
    let container = createTableDataContainer();
    container.textContent = currency.cnbMid;

    return container;
}

function createMove(currency) {
    let container = createTableDataContainer();
    container.textContent = currency.move;

    return container;
}