% AUTOMATIC CERTIFICATE GENERATOR - 18D078 
clc 
close all
home % To send the cursor to home

filename = 'PrintingDetails.xls'; % storing the Spreadsheet file name
[num,txt] = xlsread(filename);
% Read Excel sheet containing details. Text is read from the file
% seperately from numbers


len=length(txt);
% obtain length of text in excel or number of certificates to be generated

blankCertificate = imread('Blank Certificate.jpg');

% Reading blank certificate image

position =  [1250 1080]; 
blankCertificate = insertText(blankCertificate,position,'Praveen Kumar A','FontSize',30,'Font','Times New Roman','BoxOpacity',0);


for i = 1:len
    for j = 2:2 
        text_names(i,j)=txt(i,j);
    end
end
% Obtain names from the txt variable which are in 2nd column

for i = 1:len
    for j = 3:3
      text_topic(i,j)=txt(i,j);
    end
end
% Obtain topics from the txt variable which are in 3rd column


%We can ignore the first row which is the heading

for i = 2:len
        text_all=[text_names(i,2) text_topic(i,3)]
        % combine names and topics
        
        position = [1000 500;1100 600];         
        %position = [1000 2000;660 1414];
        % obtain positions to insert on image, MSPaint or any image editor
        
        opImage = insertText(blankCertificate,position,text_all,'FontSize',100,'Font','Times New Roman','BoxOpacity',0);
        %Provide parameters for function insertText
        %Font size is 22 and opacity of box is 0 means 100% transparent
        
        figure
        imshow(opImage)        
        %Display image for Debugging
 
        
        folder = 'D:\MY PROJECTS\matlab\src\AUTOMATIC CERTIFICATE GENERATOR\Outputs';
        
        certName = sprintf('Certificate_Number#%d.png', i-1);
        
        fullFileName = fullfile(folder, certName);
        imwrite(opImage, fullFileName);
       % generate and save files with .png extension
end    
