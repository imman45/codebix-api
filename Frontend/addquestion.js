async function addQuestions() {
  alert("its working");
  const question = document.getElementById("question").value;
  const optionA = document.getElementById("optA").value;
  const optionB = document.getElementById("optB").value;
  const optionC = document.getElementById("optC").value;
  const optionD = document.getElementById("optD").value;
  const selectedAnswer = document.getElementById("crtAns").value;
  const correctAnswer = document.getElementById(selectedAnswer).value;
  const explanation = document.getElementById("explain").value;
  const category = document.getElementById("category").value;

  //Validate user input
  if (
    !question.trim() ||
    !optionA.trim() ||
    !optionB.trim() ||
    !optionC.trim() ||
    !optionD.trim() ||
    !correctAnswer.trim() ||
    !explanation.trim()
  ) {
    alert("Please fill all fields no Empty Spaces");
    return;
  }

  const url = "http://localhost:8091/teacher/addquestion";
  const requestData = {
    questionText: question,
    option_A: optionA,
    option_B: optionB,
    option_C: optionC,
    option_D: optionD,
    correctAnswer: correctAnswer,
    category: category,
    explanation: explanation,
  };

  document.getElementById("question").value = "";
  document.getElementById("optA").value = "";
  document.getElementById("optB").value = "";
  document.getElementById("optC").value = "";
  document.getElementById("optD").value = "";
  document.getElementById("explain").value = "";

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
      alert("Question Added..");
    } else {
      throw new Error("Unexpected Status code");
    }
  } catch (error) {
    console.log("Error: ", error);
  }
}
