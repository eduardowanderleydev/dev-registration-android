package br.com.ucsal.devregistration.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.ucsal.devregistration.domain.Resume;

public class ArrayRepository {

    private static List<Resume> resumes = new ArrayList<>();

    public static List<Resume> findAll(){
        return resumes;
    }

    public static void add(Resume resume){
        resumes.add(resume);
    }

    public static void addAll(Collection collection){
        resumes.addAll(collection);
    }

    public static void remove(Resume resume){
        if (resumes.contains(resume)){
            resumes.remove(resume);
        }
    }

}
