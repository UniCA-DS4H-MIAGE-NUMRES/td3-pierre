# **PizzApp - Pierre SAVE**

Voici la vidéo : https://unice-my.sharepoint.com/:v:/r/personal/pierre_save_etu_unice_fr/Documents/TP3-PizzApp.mp4?csf=1&web=1&nav=eyJyZWZlcnJhbEluZm8iOnsicmVmZXJyYWxBcHAiOiJPbmVEcml2ZUZvckJ1c2luZXNzIiwicmVmZXJyYWxBcHBQbGF0Zm9ybSI6IldlYiIsInJlZmVycmFsTW9kZSI6InZpZXciLCJyZWZlcnJhbFZpZXciOiJNeUZpbGVzTGlua0NvcHkifX0&e=NTDR2a

Sur mon Onedrive Univ, une notification par mail a été envoyé  

## **Difficultés rencontrées**

### **1. Temps**
- La principale difficulté était le **manque de temps**, étant seul pour réaliser ce projet.
- Ce projet a exigé une grande partie de mon **temps personnel** pour :
  - Comprendre le fonctionnement de **Kotlin Multiplatform Wizard (KMW)**.
  - Adapter l'ensemble des fichiers nécessaires, notamment les fichiers `libs` et `build.gradle.kts`.
  - Résoudre des problèmes spécifiques liés à l'implémentation multiplateforme.
- L'adaptation à **Kotlin Multiplatform** a représenté une courbe d'apprentissage importante et chronophage.

---

### **2. Adaptation des fichiers**
-  **Étape 0 :** La phase d'adaptation des fichiers a été particulièrement complexe :
  - L'adaptation des fichiers `libs` et `build.gradle.kts` m'a pris **une journée entière de travail**, car tout devait être compatible avec **KMW**.
  - Les autres fichiers ont nécessité plusieurs jours supplémentaires pour être adaptés, en m'appuyant sur de la **documentation en ligne**.

L'étape 0 m'a pris à elle seule plusieurs semaine...

- Pour remplacer NavHost : Mise en œuvre d'un **système de navigation multiplateforme** en utilisant des `MutableStateOf` pour gérer les écrans, ce qui a été un défi technique. Je ne sais pas pourquoi, mais la version Desktop ne veut pas naviguer entre les pages... Mais la version android marche bien.
- **Retour en arrière :** Intégrer une gestion correcte de la navigation avec un bouton "Retour" dans un contexte multiplateforme (Android/Desktop).
- Adaptation des **ViewModels** pour qu'ils soient compatibles avec **KMW**. J'ai utilisé StateFlow et MutableStateFlow
- Gestion des **images** avec un système `expect` et `actual` pour le chargement multiplateforme (Android et Desktop).

---

### **3. Base de données**
- Pour remplacer **Room**, j'avais envisagé d'utiliser **SQLDelight** comme alternative multiplateforme.
- Par manque de temps, je n'ai pas pu l'implémenter. Cependant, trouver une alternative viable n'a pas été une tâche particulièrement complexe.

---

## **Réalisation**

- À ce jour, j'ai réussi à implémenter les fonctionnalités suivantes :
  - La page **"WelcomeScreen"** avec une interface conviviale et des boutons de navigation.
  - La page **"MenuScreen"**, affichant les différentes pizzas disponibles, avec une navigation vers les détails des pizzas.
  - Une page **"DetailPizza"** incluant un slider interactif pour ajuster les options, comme le supplément de fromage.
  - Une page de récapitulatif de commande (**"CaddyScreen"**), permettant de visualiser les pizzas ajoutées avec leurs prix totaux.
  - La gestion du bouton "Retour" dans toutes les pages.

---

## **Axes d’amélioration**

1. **Base de données multiplateforme** :
   - Intégrer **SQLDelight** pour une gestion efficace des données sur Android et Desktop.
   - Ajouter une fonctionnalité d'historique complet des commandes.
   - COMPRENDRE POURQUOI LA VERSION DESKTOP NE GERE PAS LA NAVIGATION !!! ALORS QUE ANDROID LE FAIT !! 

2. **Tests multiplateformes** :
   - Tester davantage les fonctionnalités sur Desktop pour garantir une expérience utilisateur homogène avec Android.

3. **Interface utilisateur** :
   - Améliorer l'apparence des écrans en utilisant davantage de composants **Material Design**.

