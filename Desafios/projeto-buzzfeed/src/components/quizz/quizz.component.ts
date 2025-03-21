import { Component, OnInit } from '@angular/core';
import { NgIf, NgFor, NgClass } from '@angular/common';
import quizz_questions from '../../assets/data/quizz_questions.json';

@Component({
  selector: 'app-quizz',
  standalone: true,
  imports: [
    NgIf,
    NgFor,
    NgClass
  ],
  templateUrl: './quizz.component.html',
  styleUrls: ['./quizz.component.css']
})
export class QuizzComponent implements OnInit {

  title: string = '';

  questions: any;
  questionSelected: any;

  answers: string[] = [];
  answerSelected: string = '';

  questionIndex: number = 0;
  questionMaxIndex: number = 0;

  finished: boolean = false;

  characters: any;
  name: string = '';
  characterImage: string = '';
  characterDescription: string = '';

  isVillain: boolean = false;
  backgroundClass: string = '';
  backgroundClassTwo: string = '';

  constructor() { }

  ngOnInit(): void {
    if (quizz_questions) {
      this.finished = false;
      this.title = quizz_questions.title;
      this.backgroundClass = 'transparent';
      this.backgroundClassTwo = 'transparent';

      this.questions = this.shuffleArray(quizz_questions.questions);
      this.questionMaxIndex = this.questions.length;

      this.questionIndex = 0;
      this.questionSelected = this.questions[this.questionIndex];
    }
  }

  shuffleArray(array: any[]): any[] {
    for (let i = array.length - 1; i > 0; i--) {
      const j = Math.floor(Math.random() * (i + 1));
      [array[i], array[j]] = [array[j], array[i]];
    }
    return array;
  }

  playerChoose(value: string) {
    this.answers.push(value);
    this.nextStep();
  }

  async nextStep() {
    this.questionIndex += 1;

    if (this.questionMaxIndex > this.questionIndex) {
      this.questionSelected = this.questions[this.questionIndex];
    } else {
      const finalAnswer: string = await this.checkResult(this.answers);
      this.finished = true;

      const possibleCharacters = quizz_questions.results[finalAnswer as keyof typeof quizz_questions.results].characters;
      const randomCharacter = possibleCharacters[Math.floor(Math.random() * possibleCharacters.length)];

      this.answerSelected = quizz_questions.results[finalAnswer as keyof typeof quizz_questions.results].message;
      this.name = randomCharacter.characterName;
      this.characterImage = randomCharacter.characterImage;
      this.characterDescription = randomCharacter.characterDescription;

      console.log("Resultado final:", finalAnswer);

      this.isVillain = finalAnswer === 'A';

      if (this.isVillain) {
        this.backgroundClass = 'villain-bg';
        this.backgroundClassTwo = 'villainContent-bg';
      } else {
        this.backgroundClass = 'hero-bg';
        this.backgroundClassTwo = 'heroContent-bg';
      }
    }
  }

  resetQuiz() {
    window.location.reload();
  }

  async checkResult(answers: string[]) {
    const result = answers.reduce((previous, current, i, arr) => {
      if (
        arr.filter(item => item === previous).length >
        arr.filter(item => item === current).length
      ) {
        return previous;
      } else {
        return current;
      }
    });

    return result;
  }
}
