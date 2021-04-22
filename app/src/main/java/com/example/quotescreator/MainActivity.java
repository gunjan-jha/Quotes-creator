package com.example.quotescreator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import static android.widget.LinearLayout.HORIZONTAL;
import static android.widget.LinearLayout.VERTICAL;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rcv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Quotes>quotes=new ArrayList<>();
        quotes.add(new Quotes ("\"All our dreams can come true, if we have the courage to pursue them.\"", "– Walt Disney"));
        quotes.add(new Quotes("\"The Pessimist Sees Difficulty In Every Opportunity.The Optimist Sees Opportunity In Every Difficulty\"","– Winston Churchill"));
        quotes.add(new Quotes("\"Don’t Let Yesterday Take Up Too Much Of Today.\"","– Will Rogers"));
        quotes.add(new Quotes("\"You Learn More From Failure Than From Success. Don’t Let It Stop You. Failure Builds Character.\""," – Unknown"));
        quotes.add(new Quotes("\"It’s Not Whether You Get Knocked Down, It’s Whether You Get Up.”"," – Inspirational Quote By Vince Lombardi"));
        quotes.add(new Quotes("\"If You Are Working On Something That You Really Care About, You Don’t Have To Be Pushed. The Vision Pulls You.”" ,"– Steve Jobs"));
        quotes.add(new Quotes("\"People Who Are Crazy Enough To Think They Can Change The World, Are The Ones Who Do.”", "– Rob Siltanen"));
        quotes.add(new Quotes("\"Failure Will Never Overtake Me If My Determination To Succeed Is Strong Enough.”" ,"– Og Mandino"));
        quotes.add(new Quotes("\"Entrepreneurs Are Great At Dealing With Uncertainty And Also Very Good At Minimizing Risk. That’s The Classic Entrepreneur\".","– Mohnish Pabrai"));
        quotes.add(new Quotes("\"If You Are Working On Something That You Really Care About, You Don’t Have To Be Pushed. The Vision Pulls You.”"," – Steve Jobs"));
        quotes.add(new Quotes("\"If You Are Working On Something That You Really Care About, You Don’t Have To Be Pushed. The Vision Pulls You.” ","– Steve Jobs"));
        quotes.add(new Quotes("\"If You Are Working On Something That You Really Care About, You Don’t Have To Be Pushed. The Vision Pulls You.” ","– Steve Jobs"));
        quotes.add(new Quotes("\"If You Are Working On Something That You Really Care About, You Don’t Have To Be Pushed. The Vision Pulls You.” ","– Steve Jobs"));
        quotes.add(new Quotes("\"If You Are Working On Something That You Really Care About, You Don’t Have To Be Pushed. The Vision Pulls You.” ","– Steve Jobs"));
        quotes.add(new Quotes("\"If You Are Working On Something That You Really Care About, You Don’t Have To Be Pushed. The Vision Pulls You.” ","– Steve Jobs"));
        quotes.add(new Quotes("\"We May Encounter Many Defeats But We Must Not Be Defeated.”"," – Maya Angelou"));
        quotes.add(new Quotes("\"Imagine Your Life Is Perfect In Every Respect; What Would It Look Like?”"," – Brian Tracy"));
        quotes.add(new Quotes("\"We Generate Fears While We Sit. We Overcome Them By Action.”"," – Dr. Henry Link"));
        quotes.add(new Quotes("\"Whether You Think You Can Or Think You Can’t, You’re Right.” ","– Quote By Henry Ford"));
        quotes.add(new Quotes("\"Security Is Mostly A Superstition. Life Is Either A Daring Adventure Or Nothing.” ","– Life Quote By Helen Keller"));
        quotes.add(new Quotes("\"The Man Who Has Confidence In Himself Gains The Confidence Of Others.” ","– Hasidic Proverb"));
        quotes.add(new Quotes("\"The Only Limit To Our Realization Of Tomorrow Will Be Our Doubts Of Today.” ","– Motivational Quote By Franklin D. Roosevelt"));
        quotes.add(new Quotes("\"Creativity Is Intelligence Having Fun.”"," – Albert Einstein"));
        quotes.add(new Quotes("\"What You Lack In Talent Can Be Made Up With Desire, Hustle And Giving 110% All The Time.”"," – Don Zimmer"));
        quotes.add(new Quotes("\"Do What You Can With All You Have, Wherever You Are.”"," – Theodore Roosevelt"));
        quotes.add(new Quotes("\"Develop An ‘Attitude Of Gratitude’. Say Thank You To Everyone You " +
                "Meet For Everything They Do For You.”"," – Encouraging Quote By Brian Tracy"));
        quotes.add(new Quotes("\"You Are Never Too Old To Set Another Goal Or To Dream A New Dream.”"," – C.S. Lewis"));
        quotes.add(new Quotes("\"To See What Is Right And Not Do It Is A Lack Of Courage.” ","– Confucius"));
        quotes.add(new Quotes("\"Reading Is To The Mind, As Exercise Is To The Body.”"," – Brian Tracy"));
        quotes.add(new Quotes("\"Fake It Until You Make It! Act As If You Had All The Confidence You Require Until It Becomes Your Reality.”"," – Brian Tracy"));
        rcv=(RecyclerView)findViewById(R.id.rcv);
        rcv.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration itemDecor = new DividerItemDecoration(this,VERTICAL);
        rcv.addItemDecoration(itemDecor);
        MyAdapter mAdapter=new MyAdapter(this,quotes);
        rcv.setAdapter(mAdapter);
    }

}