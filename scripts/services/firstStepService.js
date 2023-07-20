const regRadio = document.getElementById('reg');
const driverRadio = document.getElementById('driver');
const firstExtraOptions = document.querySelector('.extra-options-first');

const firstCheckInput = () => {
    if (regRadio.checked || driverRadio.checked) {
        firstExtraOptions.style.display = 'block';
    } else {
        firstExtraOptions.style.display = 'none';
    }
};

regRadio.addEventListener('change', firstCheckInput);
driverRadio.addEventListener('change', firstCheckInput);
