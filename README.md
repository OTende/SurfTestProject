### Cocktail Bar

1. Реализовано следующее:
- Создание коктейлей, в том числе с загрузкой картинки из галереи **(Задание 1)**

- Вывод информации и её удаление

- Сохранение информации

- Возможность делиться коктейлями **(Задание 2)**

- Удаление коктейлей **(Задание 3)**
  
  Было реализовано частично:

- Меню добавления ингредиентов (не удалось использовать созданный layout)

- Анимации на главном экране (делалось в последнюю очередь)
  
  Скриншоты:
  
   <img title="" src="https://github.com/OTende/SurfTestProject/blob/main/screenshots/1.png?raw=true" alt="Screenshot" width="205"><img title="" src="https://github.com/OTende/SurfTestProject/blob/main/screenshots/2.png?raw=true" alt="Screenshot" width="205">
  
  <img src="https://github.com/OTende/SurfTestProject/blob/main/screenshots/3.png?raw=true" title="" alt="" width="208"><img title="" src="https://github.com/OTende/SurfTestProject/blob/main/screenshots/4.png?raw=true" alt="" width="207">
  
  <img title="" src="https://github.com/OTende/SurfTestProject/blob/main/screenshots/5.png?raw=true" alt="Screenshot" width="198"><img title="" src="https://github.com/OTende/SurfTestProject/blob/main/screenshots/6.png?raw=true" alt="Screenshot" width="200">
2. Для хранения данных использован Room из-за относительной комплексности данных и их возможного расширения

Для перемещения по экранам использован Navigation Component и подход Single Activity

Для DI используется Hilt (был подключен ещё в начале проекта, на данный момент от него возможно отказаться)

Также были исползованы View Binding, Material и Coroutines

3. Проект разработан с использованием шаблона MVVM с LiveData
