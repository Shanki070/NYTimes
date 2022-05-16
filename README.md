# NYTimes Demo
This application is a demo application used to fetch the most recent news from The New York Times.


# Descriptions
This application is made based upon the MVVM pattern, Databinding, Room, Hilt.
The news is fetched based upon the Most Popular API: https://developer.nytimes.com/docs/most-popular-product/1/overview


# How to Run the Project
Step1: Obtain an API key from the NY Times by following the link https://developer.nytimes.com/get-started.
Step2: In class com.example.nytimes.network.NetworkConstant, update the api key
    const val API_KEY = “sample-api-key”
Step3: Import the project in the android studio and run it.