document.addEventListener("DOMContentLoaded", async function () {
  // const quizData = [
  //   {
  //     id: "1",
  //     questionText: "What is JavaScript used for?",
  //     optA: "a) A method of data storage",
  //     optB: "b) A programming language for web development",
  //     optC: "c) A type of loop",
  //     optD: "d) A conditional statement",
  //   },
  //   {
  //     id: "2",
  //     questionText: "What does the acronym 'DOM' stand for in web development?",
  //     optA: "a) Document Object Model",
  //     optB: "b) Data Object Model",
  //     optC: "c) Document Orientation Model",
  //     optD: "d) Document Output Mechanism",
  //   },
  //   {
  //     id: "33",
  //     questionText:
  //       "What does the acronym 'AJAX' stand for in web development?",
  //     optA: "a) Asynchronous JavaScript and XML",
  //     optB: "b) Advanced JavaScript and XML",
  //     optC: "c) Automated JavaScript and XML",
  //     optD: "d) Application JavaScript and XML",
  //   },
  //   // Add more questions as needed
  // ];

  const quizContainer = document.getElementById("question-container");
  const submitButton = document.getElementById("submit-button");

  // Function to insert questions into the HTML
  function insertQuestions(data) {
    data.forEach((question, index) => {
      const { id, questionText, option_A, option_B, option_C, option_D } =
        question;

      const questionElement = document.createElement("div");
      questionElement.classList.add("question-element");
      questionElement.id = id;
      questionElement.innerHTML = `<p>${index + 1}. ${questionText}</p>`;

      // Create option elements
      const optiondiv = document.createElement("div");
      optiondiv.classList.add("options");
      alloption = [option_A, option_B, option_C, option_D];

      alloption.forEach((option) => {
        const optionElement = document.createElement("input");
        optionElement.type = "radio";
        optionElement.name = `question-${index}`;
        optionElement.value = option;

        const labelElement = document.createElement("label");
        labelElement.innerHTML = option;
        labelElement.addEventListener("click", () => {
          optionElement.checked = true;
        });

        optiondiv.appendChild(optionElement);
        optiondiv.appendChild(labelElement);
        optiondiv.appendChild(document.createElement("br"));
      });
      questionElement.appendChild(optiondiv);
      // Append the question to the container
      quizContainer.appendChild(questionElement);
      quizContainer.appendChild(document.createElement("hr"));
    });
  }

  submitButton.addEventListener("click", async () => {
    const selectedAns = [];
    const questions = quizContainer.querySelectorAll(".question-element");
    questions.forEach((question, index) => {
      const selectedOption = question.querySelector("input:checked");
      if (selectedOption) {
        selectedAns.push({
          id: question.id,
          answer: selectedOption.value,
        });
      }
    });
    console.log(selectedAns);

    url = "http://localhost:8090/student/submitquiz";
    const requestData = selectedAns;
    try {
      await fetch(url, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(requestData),
      })
        .then((response) => {
          if (!response.ok) {
            throw new Error("wrong");
          }
          return response.json();
        })
        .then((data) => {
          console.log(data);
          alert("Your score " + data);
        })
        .catch((error) => {
          console.log("pronblrm: " + error);
        });
    } catch (error) {
      console.log(error);
    }
  });

  url = "http://localhost:8090/student/getquiz";
  let quizData;
  try {
    await fetch(url, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      },
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("wrong");
        }
        return response.json();
      })
      .then((data) => {
        console.log(data);
        quizData = data;
      })
      .catch((error) => {
        console.log("pronblrm: " + error);
      });
  } catch (error) {
    console.log(error);
  }

  // Insert questions into the HTML
  console.log("hello");
  insertQuestions(quizData);

  // Event handler for the submission button
});
