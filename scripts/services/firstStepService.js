const regRadio = document.getElementById('reg');
const driverRadio = document.getElementById('driver');
const filesContainer = document.querySelector('.files');
const additionalPanels = document.querySelector('.additional-panels');

regRadio.addEventListener('change', () => {
  if (regRadio.checked) {
    filesContainer.style.display = 'block';
    additionalPanels.style.display = 'block';
  } else {
    filesContainer.style.display = 'none';
    additionalPanels.style.display = 'none';
  }
});

driverRadio.addEventListener('change', () => {
  if (driverRadio.checked) {
    filesContainer.style.display = 'block';
    additionalPanels.style.display = 'block';
  } else {
    filesContainer.style.display = 'none';
    additionalPanels.style.display = 'none';
  }
});