import React from 'react';
import {
  NativeModules,
  StyleSheet,
  ScrollView,
  Button,
  View,
  Text,
  TextInput,
  StatusBar,
} from 'react-native';

const {RNHelpCrunch} = NativeModules;

import {Colors} from 'react-native/Libraries/NewAppScreen';

const App: () => React$Node = () => {
  return (
    <>
      <StatusBar barStyle="dark-content" />
      <ScrollView
        contentInsetAdjustmentBehavior="automatic"
        style={styles.scrollView}>
        <View style={styles.body}>
          <View style={styles.sectionContainer}>
            <Text style={styles.sectionTitle}>Step One</Text>
            <Text style={styles.sectionDescription}>Focus TextInput</Text>
            <TextInput style={styles.input} />
          </View>
          <View style={styles.sectionContainer}>
            <Text style={styles.sectionTitle}>Step Two</Text>
            <Text style={styles.sectionDescription}>Open Chat</Text>
            <Button onPress={() => RNHelpCrunch.show()} title="Open chat" />
          </View>
          <View style={styles.sectionContainer}>
            <Text style={styles.sectionTitle}>Step Three</Text>
            <Text style={styles.sectionDescription}>Focus TextInput</Text>
            <TextInput style={styles.input} />
          </View>
        </View>
      </ScrollView>
    </>
  );
};

const styles = StyleSheet.create({
  scrollView: {
    backgroundColor: Colors.lighter,
  },
  body: {
    backgroundColor: Colors.white,
  },
  sectionContainer: {
    marginTop: 32,
    paddingHorizontal: 24,
  },
  sectionTitle: {
    fontSize: 24,
    fontWeight: '600',
    color: Colors.black,
  },
  sectionDescription: {
    marginTop: 8,
    fontSize: 18,
    fontWeight: '400',
    color: Colors.dark,
  },
  input: {
    marginTop: 10,
    fontSize: 18,
    borderWidth: 1,
  },
});

export default App;
