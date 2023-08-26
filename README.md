# Описание
Реализация одного из методов самонастройки для алгоритма дифференциальной эволюции
# 
![image](https://github.com/miniGadza/SelfTuning_DifferentialEvolution_Algorithm/assets/140532458/4289e205-351d-48c1-bcb5-bbf1c8edb93e)
# Дифференциальная эволюция (DE)
Алгоритм DE ищет точку глобального оптимума в D-мерном пространстве вещественных
переменных.
### Основные этапы
![image](https://github.com/miniGadza/SelfTuning_DifferentialEvolution_Algorithm/assets/140532458/20637f57-d2fa-47c2-bc07-1be8fe1f7046)
### 1. Инициализация
Алгоритм начинается с шага инициализации, на котором задается популяция
из NP D-мерных векторов вещественных чисел, каждый вектор, также называемый
геномом или хромосомой, формирует решение-кандидат для многомерной задачи
оптимизации :
![image](https://github.com/miniGadza/SelfTuning_DifferentialEvolution_Algorithm/assets/140532458/195d8fc7-ec6a-4450-ae06-b9f738a5ece6)
### 2. Мутация
В DE вектор-родитель из текущего поколения
называется целевым вектором, вектор, полученный после мутации – донор-вектором, и,
наконец, потомок, сформированный после скрещивания – пробным вектором. В
простейших формах DE для создания донор-вектора для каждого вектора xi
, i=1…NP из
популяции, три различных вектора Xr1, Xr2, Xr3 выбираются случайно из текущей
популяции, при этом r1!=r2!=r3!=i. После этого разница между двумя векторами
умножается на масштабирующий фактор F, и прибавляется к координатам третьего
вектора :
![image](https://github.com/miniGadza/SelfTuning_DifferentialEvolution_Algorithm/assets/140532458/0a892206-f1da-4059-a9c5-5667cc61ddbd)
#### Иллюстрация в двумерном пространстве :
![image](https://github.com/miniGadza/SelfTuning_DifferentialEvolution_Algorithm/assets/140532458/9f96a71b-c06b-4210-b68b-5f557d0e32bb)
### 3. Скрещивание 
DE использует оператор скрещивания для повышения разнообразия в популяции после
мутации. Донор-вектор обменивается компонентами с целевым вектором для
формирования пробного вектора U :
![image](https://github.com/miniGadza/SelfTuning_DifferentialEvolution_Algorithm/assets/140532458/28c39e4a-4b8d-4a19-8a56-4b722768a363)
#### Иллюстрация :
![image](https://github.com/miniGadza/SelfTuning_DifferentialEvolution_Algorithm/assets/140532458/68c507d7-dd73-4f71-8d71-5487d48cb9db)

jrand – случайный индекс [1, D], Cr – параметр, вероятность скрещивания
### 4. Селекция
Для того чтобы размер популяции оставался постоянным, используется селекция,
которая определяет, улучшился ли пробный вектор по сравнению с целевым :

![image](https://github.com/miniGadza/SelfTuning_DifferentialEvolution_Algorithm/assets/140532458/1e8c129c-8934-40e6-beb9-49fe71a5fc49)

Если пробный вектор лучше или имеет ту же пригодность, он заменяет
целевой вектор, в противном случае отбрасывается.

## Псевдокод для алгоритма дифференциальной эволюции с биноминальным скрещиванием
![image](https://github.com/miniGadza/SelfTuning_DifferentialEvolution_Algorithm/assets/140532458/2612134a-3e88-47c9-a418-8acc93e7ab49)

| [<a href="https://conf.sfu-kras.ru/sites/mn2014/pdf/d02/s12/s12_012.pdf)https://conf.sfu-kras.ru/sites/mn2014/pdf/d02/s12/s12_012.pdf">Источник 1</a>]|
| ------ | [<a href="https://www.researchgate.net/publication/220380793_Differential_Evolution_A_Survey_of_the_State-of-the-Art?_tp=eyJjb250ZXh0Ijp7InBhZ2UiOiJfZGlyZWN0In19">Источник 2</a>] |









