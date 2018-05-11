package com.example.fauza.daoexercise.util;

import com.example.fauza.daoexercise.entity.People;

import java.util.List;

public interface IOnPostExecute {
    void passData(List<People> peoples);
}