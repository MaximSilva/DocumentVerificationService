const identityRadio = document.getElementById('identity');
const passportRadio = document.getElementById('passport');
const thirdExtraOptions = document.querySelector('.extra-options-third');
const files2Div = document.querySelector('.files2');

const thirdCheckInput = () => {
    if (identityRadio.checked) {
        thirdExtraOptions.style.display = 'block';
        files2Div.style.display = 'none';
    } else {
        thirdExtraOptions.style.display = 'none';
        files2Div.style.display = 'block';
    }
};

identityRadio.addEventListener('change', thirdCheckInput);
passportRadio.addEventListener('change', thirdCheckInput);
         
      
          
    