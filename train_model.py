import pandas as pd
import numpy as np
from sklearn.model_selection import train_test_split
from sklearn.ensemble import RandomForestClassifier
from sklearn.preprocessing import StandardScaler
from sklearn.metrics import classification_report, accuracy_score
import pickle

df = pd.read_csv('languages.csv')

def extract_features(language):
    words = language.split(',')
    lengths = [len(word) for word in words]
    return [len(words), np.mean(lengths), np.std(lengths), max(lengths), min(lengths)]

df['features'] = df['language'].apply(extract_features)

X = np.vstack(df['features'].values)
y = df['is_code'].astype(int)

sc = StandardScaler()
X = sc.fit_transform(X)

X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

model = RandomForestClassifier(n_estimators=100, random_state=42)
model.fit(X_train, y_train)

with open('sardinas_patterson_model.pkl', 'wb') as file:
    pickle.dump(model, file)

y_pred = model.predict(X_test)

print("Rapport de classification :")
print(classification_report(y_test, y_pred))

accuracy = accuracy_score(y_test, y_pred)
print(f"Précision du modèle : {accuracy * 100:.2f} %")

def predict_language(language):
    avg_length, unique_words, num_words, max_length, min_length = extract_features(language)
    new_features = [[num_words, avg_length, unique_words, max_length, min_length]]
    new_features = sc.transform(new_features)
    prediction = model.predict(new_features)
    return "Code" if prediction[0] == 1 else "Non Code"

language_example = "0110,1000,0011"
prediction_result = predict_language(language_example)
print(f"Le langage '{language_example}' est prédit comme étant : {prediction_result}")
