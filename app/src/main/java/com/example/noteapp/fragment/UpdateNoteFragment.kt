package com.example.noteapp.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.noteapp.MainActivity
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentUpdateNoteBinding
import com.example.noteapp.model.Note
import com.example.noteapp.viewmodel.NoteViewModel

class UpdateNoteFragment : Fragment(R.layout.fragment_update_note) {

    private var _binding : FragmentUpdateNoteBinding? = null
    private val binding get() = _binding!!
    private lateinit var noteViewModel: NoteViewModel
    private lateinit var currentNote : Note
    private val args: UpdateNoteFragmentArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteViewModel = (activity as MainActivity).noteViewModel
        currentNote = args.note!!

        binding.editNoteTitle.setText(currentNote.title)
        binding.editNoteDesc.setText(currentNote.body)

        binding.editNoteFab.setOnClickListener {
            val title = binding.editNoteTitle.text.toString().trim()
            val body = binding.editNoteDesc.text.toString().trim()

            if (title.isNotEmpty()) {
                val note = Note(currentNote.id, title, body)
                noteViewModel.updateNote(note)
                Toast.makeText(context, "Update Note Successful", Toast.LENGTH_LONG).show()
                view.findNavController().navigate(R.id.action_updateNoteFragment_to_homeFragment)
            } else {
                Toast.makeText(context, "Please enter note title", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun deleteNote() {
        AlertDialog.Builder(activity).apply {
            setTitle("Delete Note")
            setMessage("Do you sure to delete this Note?")
            setPositiveButton("Delete"){_,_, ->
                noteViewModel.deleteNote(currentNote)
                view?.findNavController()?.navigate(R.id.action_updateNoteFragment_to_homeFragment)
            }
            setNegativeButton("Cancel", null)
        }.create().show()
    }





    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        menu.clear()
        inflater.inflate(R.menu.menu_edit_note, menu)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.deleteMenu -> {
                deleteNote()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}