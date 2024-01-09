async function addQuiz() {
  alert("its working");
  const title = document.getElementById("Quiztitle").value;
  const category = document.getElementById("category").value;
  const numQ = document.getElementById("numQ").value;

  console.log(title, category, numQ);

  //Validate user input
  if (!title.trim()) {
    alert("Please fill all fields no Empty Spaces");
    return;
  }

  const url = "http://localhost:8091/teacher/createquiz";
  const requestData = {
    tile: title,
    numQuestions: numQ,
    categoryName: category,
  };

  document.getElementById("Quiztitle").value = "";
  document.getElementById("numQ").value = "";

  //try catch
  try {
    const response = await fetch(url, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(requestData),
    });

    if (response.status === 201) {
      alert("Quiz Updated...");
    } else {
      throw new Error("Unexpected Status code");
    }
  } catch (error) {
    console.log("Error: ", error);
  }
}
