package csu.bryanreilly.partypush.Utilities;

import android.content.Context;

public class ContextGetter {
    private static ContextGetter instance;
    private Context context;

    private ContextGetter(){
        // Private instantiation because this class is a Singleton.
    }

    public static ContextGetter getInstance(){
        if(instance == null){
            instance = new ContextGetter();
        }
        return instance;
    }

    public Context getContext() throws Exception{
        if (context != null){
            return context;
        }
        else{
            throw new Exception("Context from ContextGetter is null. Try setting context first.");
        }
    }

    public void setContext(Context context){
        if (context != null)
            this.context = context;
    }
}
