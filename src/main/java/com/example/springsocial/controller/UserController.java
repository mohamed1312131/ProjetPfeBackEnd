package com.example.springsocial.controller;

import com.example.springsocial.exception.ResourceNotFoundException;
import com.example.springsocial.model.User;
import com.example.springsocial.repository.UserRepository;
import com.example.springsocial.security.CurrentUser;
import com.example.springsocial.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/me")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }

    public static double calculerMoyenne(int[] tableau) {

  // Vérifier si le tableau est vide
  if (tableau.length == 0) {
    throw new IllegalArgumentException("Le tableau est vide");
  }

  // Initialiser la somme des éléments
  double somme = 0;

  // Parcourir chaque élément du tableau et l'ajouter à la somme
  for (int element : tableau) {
    somme += element;
  }

  // Calculer la moyenne en divisant la somme par la longueur du tableau
  double moyenne = somme / tableau.length;

  // Retourner la moyenne
  return moyenne;
}


}
