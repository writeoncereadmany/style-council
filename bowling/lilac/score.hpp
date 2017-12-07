#pragma once
#include<iostream>
#include"bonus.hpp"

class round {
private:
  int frames[2];
  int frameCount;
  int final_score;
  Bonus bonus;

  bool checkIfBonus();
public:
  bool isDone;
  round();
  void addFrame(const int& frame);
  int getFinalScore();  
  char getFirstBox();
  char getSecondBox();
  char getThirdBox();

};
