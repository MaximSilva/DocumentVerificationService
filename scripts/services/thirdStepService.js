const docID = document.getElementById('ID');
  const docPass = document.getElementById('pass');
  const filesContainer = document.querySelector('.files');
  const filesContainer2 = document.querySelector('.files2');

  docID.addEventListener('change', () => {
    if (docID.checked) {
      filesContainer.style.display = 'block';
      filesContainer2.style.display = 'none';
    } else {
      filesContainer.style.display = 'none';
    }
  });

  docPass.addEventListener('change', () => {
    if (docPass.checked) {
      filesContainer2.style.display = 'block';
      filesContainer.style.display = 'none';
    } else {
      filesContainer2.style.display = 'none';
    }
  });
         
      
          
    