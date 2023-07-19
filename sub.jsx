const sendFormData = async (data) => {
    try {
      const response = await fetch('/submit', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
      });
  
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
  
      const result = await response.json();
      console.log(result); 
    } catch (error) {
      console.error('Error:', error);
    }
  };
  

  const handleSubmit = (event) => {
    event.preventDefault();
    const form = event.target;
    const formData = new FormData(form);
  

    const fileInputs = form.querySelectorAll('input[type="file"]');
    fileInputs.forEach((input) => {
      const name = input.name;
      const file = input.files[0];
      formData.append(name, file);
    });
  

    sendFormData(formData);
  };
  

  const form = document.querySelector('.signature-pad-from');
  form.addEventListener('submit', handleSubmit);