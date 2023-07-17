const templates = [
    '<h2>1 Крок</h2><p>Вміст першого кроку</p>',
    '<h2>2 Крок</h2><p>Вміст другого кроку</p>',
    '<h2>3 Крок</h2><p>Вміст третього кроку</p>',
    '<h2>4 Крок</h2><p>Вміст четвертого кроку</p>',
    '<h2>5 Крок</h2><p>Вміст пятого кроку</p>'
  ];
  
  let currentStep = 0;
  const rootElement = document.getElementById('root');
  
  function renderStep() {
    rootElement.innerHTML = templates[currentStep];
  }
  
  function handlePrevButtonClick() {
    if (currentStep > 0) {
      currentStep--;
      renderStep();
    }
  }
  
  function handleNextButtonClick() {
    if (currentStep < templates.length - 1) {
      currentStep++;
      renderStep();
    }
  }
  
  document.getElementById('prevButton').addEventListener('click', handlePrevButtonClick);
  document.getElementById('nextButton').addEventListener('click', handleNextButtonClick);
  
  renderStep();