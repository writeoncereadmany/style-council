#pragma once
#include<string>
#include<vector>
#include<iostream>
#include"score.hpp"
#include"linkedList.hpp"
#include"constants.hpp"

class Player {
private:
  std::string name;  
  int turn;
  bool firstOrSecond;
  round scores [TURNS];
  linkedList<round> activeQueue;
  void removeDoneFromList();
  void addFrameToDone(const int& frame);
public:
  Player();
  void setName(std::string aName);
  std::string getName();
  int getScore();
  void display();
  bool makeBowl(const int& score);
};

void addFrame(round& y, void* x);
