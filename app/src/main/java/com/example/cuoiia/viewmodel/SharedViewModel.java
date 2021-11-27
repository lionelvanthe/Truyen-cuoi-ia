package com.example.cuoiia.viewmodel;

import android.app.Application;
import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import com.example.cuoiia.model.Story;
import com.example.cuoiia.model.Topic;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SharedViewModel extends BaseViewModel{
    private AssetManager assetManager;
    private List<Topic> listTopic = new ArrayList<>();
    private List<Story> listStory = new ArrayList<>();

    private MutableLiveData<Integer> storyPosition = new MutableLiveData<>();
    private MutableLiveData<String> nameTopic = new MutableLiveData<>();

    public SharedViewModel(@NonNull Application application) {
        super(application);
        assetManager = application.getAssets();
    }

    public void setTopics(){
        try{
            InputStream is = null;
            String[] files = assetManager.list("icon");
            for(String file : files){
                is = assetManager.open("icon/" + file);
                listTopic.add(new Topic(file.replace(".png", ""), BitmapFactory.decodeStream(is)));
            }
            if(is != null){
                is.close();
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setStories(String fileName){
        listStory.clear();

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(assetManager.open("data/"  + fileName +".txt")));
            String line;
            String story = "";
            while((line = bufferedReader.readLine()) != null){
                story += line + "\n";
                //line = bufferedReader.readLine();
            }
            String[] split = story.split("','0'\\);");
            for(int i = 0 ; i < split.length -1 ; i++ ){
                String b;
                if(i == 0){
                    b = split[i].split("\n", 2)[0];
                }
                else{
                    b = split[i].split("\n", i + 2)[1];
                }
                listStory.add(new Story(b, split[i].replace(b, "")));

            }
            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public MutableLiveData<Integer> getStoryPosition() {
        return storyPosition;
    }

    public void setStoryPosition(int storyPosition) {
        this.storyPosition.postValue(storyPosition);
    }

    public MutableLiveData<String> getNameTopic() {
        return nameTopic;
    }

    public void setNameTopic(String nameTopic) {
        this.nameTopic.postValue(nameTopic);
    }

    public List<Topic> getListTopic() {
        return listTopic;
    }

    public List<Story> getListStory() {
        return listStory;
    }
}
