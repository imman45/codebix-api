var data1 = localStorage.getItem("datas");
var data = JSON.parse(data1);
console.log(data);
const questionsPerPage = 5;
let currentPage = 0;
const questionContainer = document.querySelector(".question-container");
const paginationDiv = document.querySelector(".pagination");
const questionDiv = document.createElement("div"); // Container for questions

// Function to clear the questions
function clearQuestions() {
  questionDiv.innerHTML = ""; // Clear the question container
}

// Function to insert questions
function insertQuestions(page) {
  clearQuestions(); // Clear existing questions

  for (
    let i = page * questionsPerPage;
    i < (page + 1) * questionsPerPage;
    i++
  ) {
    if (i < data.length) {
      const question = data[i];
      const questionNumber = i + 1;

      questionDiv.classList.add("questiondiv");
      const question_Text = document.createElement("div");
      question_Text.classList.add("questionText");
      question_Text.textContent = questionNumber + ") " + question.questionText;
      questionDiv.appendChild(question_Text);
      questionDiv.appendChild(document.createElement("br"));

      const options = ["option_A", "option_B", "option_C", "option_D"];
      let explanationAdded = false;

      const alloption = document.createElement("div");
      alloption.classList.add("alloption");
      options.forEach((option) => {
        const optionText = document.createElement("div");
        optionText.textContent =
          option[option.length - 1] + " " + question[option];
        optionText.classList.add("option");

        optionText.addEventListener("click", () => {
          if (
            question[option] === question.correctAnswer &&
            !explanationAdded
          ) {
            optionText.classList.add("correct");

            const explaindiv = document.createElement("div");
            explaindiv.classList.add("explaindiv");

            const explanationHeading = document.createElement("div");
            explanationHeading.textContent = "E X P L A N A T I O N";
            explanationHeading.classList.add("explainhead");
            explaindiv.appendChild(explanationHeading);

            const explanation = document.createElement("div");
            explanation.classList.add("explanation");
            explanation.textContent = question.explanation;
            explaindiv.appendChild(explanation);

            alloption.insertBefore(explaindiv, alloption.querySelector("hr"));
            explanationAdded = true;
          } else {
            optionText.classList.add("incorrect");
          }
        });
        alloption.appendChild(optionText);
        alloption.appendChild(document.createElement("br"));
      });

      alloption.appendChild(document.createElement("hr"));
      questionDiv.appendChild(alloption);
      questionContainer.insertBefore(
        questionDiv,
        questionContainer.querySelector(".pagination")
      );
    }
  }
}

// Function to set up pagination buttons
function setupPaginationButtons() {
  const prevButton = document.createElement("button");
  prevButton.className = "buttons";
  prevButton.textContent = "<< Prev";
  prevButton.id = "prev-button";
  prevButton.disabled = true;

  const nextButton = document.createElement("button");
  nextButton.className = "buttons";
  nextButton.textContent = "Next >>";
  nextButton.id = "next-button";

  paginationDiv.appendChild(prevButton);
  paginationDiv.appendChild(nextButton);

  prevButton.addEventListener("click", () => {
    if (currentPage > 0) {
      currentPage--;
      insertQuestions(currentPage);
      nextButton.removeAttribute("disabled");
      if (currentPage === 0) {
        prevButton.setAttribute("disabled", "true");
      }
    }
  });

  nextButton.addEventListener("click", () => {
    if ((currentPage + 1) * questionsPerPage < data.length) {
      currentPage++;
      insertQuestions(currentPage);
      prevButton.removeAttribute("disabled");
      if ((currentPage + 1) * questionsPerPage >= data.length) {
        nextButton.setAttribute("disabled", "true");
      }
    }
  });
}

// Initial setup
insertQuestions(currentPage);
setupPaginationButtons();
