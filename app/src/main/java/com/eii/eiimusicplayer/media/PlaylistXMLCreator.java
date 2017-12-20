package com.eii.eiimusicplayer.media;

import android.content.Context;
import android.os.Environment;
import android.util.Xml;

import com.eii.eiimusicplayer.media.pojo.Song;

import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

/**
 * Created by Carla on 20/12/2017.
 */

public class PlaylistXMLCreator {
    private String xmlString(List<Song> songs) {
        XmlSerializer serializer = Xml.newSerializer();
        StringWriter writer = new StringWriter();
        try {
            serializer.setOutput(writer);
            serializer.startDocument("UTF-8", true);
            serializer.startTag("", "songs");
            serializer.attribute("", "number", String.valueOf(songs.size()));
            for (Song song : songs) {
                serializer.startTag("", "song");
                serializer.startTag("", "title");
                serializer.text(song.getTitle());
                serializer.endTag("", "title");
                serializer.startTag("", "album");
                serializer.text(song.getAlbum().getTitle());
                serializer.endTag("", "album");
                serializer.startTag("", "artist");
                serializer.text(song.getArtist().getName());
                serializer.endTag("", "artist");
                serializer.endTag("", "song");
            }
            serializer.endTag("", "songs");
            serializer.endDocument();
            return writer.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void writeXmlFile(Context context, String fileName, List<Song> songs) throws IOException {
        if (!isExternalStorageWritable()) {
            throw new IOException("External storage not writable");
        }
        File playlistDir = createNewPlaylist(context, fileName + ".xml");
        FileWriter writer = new FileWriter(playlistDir);
        writer.write(xmlString(songs));
        writer.close();
    }

    private File createNewPlaylist(Context context, String fileName) {
        // Get the directory for the app's private documents directory.
        File file = new File(context.getExternalFilesDir(
                Environment.DIRECTORY_DOCUMENTS), fileName);
        if (!file.mkdirs()) {
            // directory not created
        }
        return file;
    }

    /* Checks if external storage is available for read and write */
    private boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }
}
