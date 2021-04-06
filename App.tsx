/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * Generated with the TypeScript template
 * https://github.com/react-native-community/react-native-template-typescript
 *
 * @format
 */

import React from 'react';
import {
  StyleSheet,
  Text,
  View,
  TouchableOpacity,
  NativeModules,
} from 'react-native';

const App = () => {
  const printHandler = () => {
    console.log('Trying to pring');
    NativeModules.PaxModule.print();
  };

  return (
    <View style={styles.center}>
      <TouchableOpacity onPress={printHandler} style={styles.button}>
        <Text style={styles.text}>Print</Text>
      </TouchableOpacity>
    </View>
  );
};

const styles = StyleSheet.create({
  center: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  button: {
    backgroundColor: 'purple',
    width: 200,
    height: 40,
    justifyContent: 'center',
    alignItems: 'center',
    borderRadius: 12,
  },
  text: {
    color: 'white',
  },
});

export default App;
