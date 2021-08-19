package master.flame.danmaku.danmaku.loader.android;

import android.content.Loader;
import android.net.Uri;

import java.io.InputStream;

import master.flame.danmaku.danmaku.loader.ILoader;
import master.flame.danmaku.danmaku.loader.IllegalDataException;
import master.flame.danmaku.danmaku.parser.IDataSource;
import master.flame.danmaku.danmaku.parser.android.JSONSource;

public class LongChatDanmakuLoader implements ILoader {
    private JSONSource dataSource;
    private static volatile LongChatDanmakuLoader instance;

    private LongChatDanmakuLoader() {
    }

    public static ILoader instance() {
        if (instance == null) {
            synchronized (AcFunDanmakuLoader.class) {
                if (instance == null)
                    instance = new LongChatDanmakuLoader();
            }
        }
        return instance;
    }

    @Override
    public IDataSource<?> getDataSource() {
        return dataSource;
    }

    @Override
    public void load(String uri) throws IllegalDataException {
        try {
            dataSource = new JSONSource(Uri.parse(uri));
        } catch (Exception e) {
            throw new IllegalDataException(e);
        }
    }

    @Override
    public void load(InputStream in) throws IllegalDataException {
        try {
            dataSource = new JSONSource(in);
        } catch (Exception e) {
            throw new IllegalDataException(e);
        }
    }

}
