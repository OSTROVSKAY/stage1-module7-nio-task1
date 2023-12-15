package com.epam.mjc.nio;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileReader {

    public Profile getDataFromFile(File file) {

        StringBuilder profileData = new StringBuilder();

        String name = null;
        Integer age = null;
        String email = null;
        Long phone = null;

        try(RandomAccessFile aFile = new RandomAccessFile(file, "r"); FileChannel inChannel = aFile.getChannel() ) {

            // Buffer size is 1024
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            while(inChannel.read(buffer) > 0) {

                buffer.flip();

                for(int j = 0; j < buffer.limit(); j++) {

                    profileData.append((char) buffer.get());

                }

            }


            String string = profileData.toString();

                String[] splitStrings = string.split("\n");

                    String[] profileFields = new String[splitStrings.length];

                    for (int i = 0; i < splitStrings.length; i++) {

                        String[] tempArray = splitStrings[i].split(" ");

                            profileFields[i] = tempArray[1];

                    }

                        name = profileFields[0];
                        age = Integer.parseInt(profileFields[1]);
                        email = profileFields[2];
                        phone = Long.parseLong(profileFields[3]);


        } catch (IOException e) {

            e.printStackTrace();
        }

        return new Profile(name,

                age,

                email,

                phone);

    }
}
