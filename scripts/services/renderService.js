const LOCALIZATION = [
    {
        title: 'Верифікація даних 1',
        subTitle: 'Крок 1',
        description: 'Оберіть тип бланку для верифікації 1',
    },
    {
        title: 'Верифікація даних 2',
        subTitle: 'Крок 2',
        description: 'Оберіть тип бланку для верифікації 2',
    },
    {
        title: 'Верифікація даних 3',
        subTitle: 'Крок 3',
        description: 'Оберіть тип бланку для верифікації 3',
    },
    {
        title: 'Верифікація даних 4',
        subTitle: 'Крок 4',
        description: 'Оберіть тип бланку для верифікації 4',
    },
    {
        title: 'Верифікація даних 5',
        subTitle: 'Крок 5',
        description: 'Оберіть тип бланку для верифікації 5',
    },
];

const steps = document.getElementsByClassName('step') || [];

//localization
const titleElement = document.getElementById('title');
const subTitleElement = document.getElementById('subTitle');
const descriptionElement = document.getElementById('description');

//navigation
const navItems = document.getElementsByClassName('nav__item') || [];
const nextButton = document.getElementById('nextButton');
const prevButton = document.getElementById('prevButton');

let currentStep = 0;

const renderNavigation = () => {
    let index = 0;
    for (const item of navItems) {
        item.classList.remove('nav__item-active');

        if (index <= currentStep) {
            item.classList.add('nav__item-active');
        }

        index++;
    }
};

const renderLocalization = () => {
    const {title, subTitle, description} = LOCALIZATION[currentStep] || {};

    titleElement.innerHTML = title;
    subTitleElement.innerHTML = subTitle;
    descriptionElement.innerHTML = description;
};

const renderStep = () => {
    for (const step of steps) {
        step.classList.remove('step-active');
    }

    steps[currentStep].classList.add('step-active');
};

const render = () => {
    renderStep();
    renderLocalization();
    renderNavigation();
};

const handlePrevButtonClick = () => {
    if (currentStep > 0) {
        currentStep--;
        render();
    }
};

const handleNextButtonClick = () => {
    if (currentStep < steps.length - 1) {
        currentStep++;
        render();
    }
};

prevButton.addEventListener('click', handlePrevButtonClick);
nextButton.addEventListener('click', handleNextButtonClick);

window.addEventListener('load', render);
