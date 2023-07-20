import React, { useState } from 'react';


const Sub = () => {

  const [frontImage, setFrontImage] = useState('');
  const [backImage, setBackImage] = useState('');
  const [series, setSeries] = useState('');
  const [number, setNumber] = useState('');
  const [date, setDate] = useState('');
  const [issuedBy, setIssuedBy] = useState('');
  const [signatureImage, setSignatureImage] = useState('');

  const apiEndpoint = "http://localhost:8080/submit"; 

  const handleSubmit = async (event) => {
    event.preventDefault();
    const form = event.target;
    const formData = new FormData(form);

    try {
      const response = await fetch(apiEndpoint, { 
        method: 'POST',
        body: formData,
      });

      if (!response.ok) {
        throw new Error('Network response was not ok');
      }

      const result = await response.text();
      console.log(result);
    } catch (error) {
      console.error('Error:', error);
    }
  };

  const handleFrontImageChange = (event) => {
    const file = event.target.files[0];
    setFrontImage(file);
  };

  return (
    <form className="signature-pad-form" onSubmit={handleSubmit}>
      {}
      <label htmlFor="frontImage">Front Image:</label>
      <input type="file" id="frontImage" name="frontImage" accept=".jpg,.png,.jpeg" onChange={handleFrontImageChange} />
      {}
      <button type="submit">Submit</button>
    </form>
  );
};

export default Sub;