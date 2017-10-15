# Simple recommendation engine
Hoppy project for learning Apache Spark. I implemented a recommendation engine using Apache Mahout a while ago. Mahout uses Item-Item similarities in it's collaborative filtering algorithm. I'd like to test the same in Spark as it uses a different collaborative filtering algorithm.

Example data can be downloaded from: [https://grouplens.org/datasets/movielens/](https://grouplens.org/datasets/movielens/) 

## Api
`/train` trains the model using a csv dataset with format `userid, productId, rating`

Training should take less than a minute with the small dataset at: [https://grouplens.org/datasets/movielens/](https://grouplens.org/datasets/movielens/) 

`/recommend/userId` returns personilized recommendations for the user

## Todos
Just started this project and there's a lot of stuff to do:
* Verification of training result
* Persisting model (at the moment recommendation are not instantaneous)
* Item based recommendations
* Custom number of recommendations
